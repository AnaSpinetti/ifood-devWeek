package me.dio.carrinho.repository.services.impl;

import lombok.RequiredArgsConstructor;
import me.dio.carrinho.enumeration.FormaPagamento;
import me.dio.carrinho.model.Carrinho;
import me.dio.carrinho.model.Item;
import me.dio.carrinho.model.Restaurante;
import me.dio.carrinho.repository.CarrinhoRepository;
import me.dio.carrinho.repository.ItemRepository;
import me.dio.carrinho.repository.ProdutoRepository;
import me.dio.carrinho.repository.services.CarrinhoService;
import me.dio.carrinho.resources.dto.ItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarrinhoServiceImpl implements CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item incluirItemSacola(ItemDto itemDto) {
        Carrinho carrinho = verCarrinho(itemDto.getIdSacola());

        if(carrinho.getFechado() == true){
            throw new RuntimeException("Esse carinho está fechado");
        }

        Item itemParaSerInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .carrinho(carrinho)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Esse produto não existe");
                        }
                ))
                .build();

        List<Item> itensDoCarrinho = carrinho.getItens();

        if(itensDoCarrinho.isEmpty()){
            itensDoCarrinho.add(itemParaSerInserido);
        }else{
            Restaurante restauranteAtual = itensDoCarrinho.get(0).getProduto().getRestaurante();
            Restaurante restauranteDoitemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();

            if(restauranteAtual.equals(restauranteDoitemParaAdicionar)){
                itensDoCarrinho.add(itemParaSerInserido);
            }else{
                throw new RuntimeException("Não é possivel adicionar produtos de restaurantes diferentes");
            }
        }

        List<Double>     

        carrinhoRepository.save(carrinho);

        return itemParaSerInserido;
    }

    @Override
    public Carrinho verCarrinho(Long id) {
        return carrinhoRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Esse carrinho não existe");
                }
        );
    }

    @Override
    public Carrinho fecharCarrinho(Long id, int numeroFormaPagamento) {
        Carrinho carrinho = verCarrinho(id);

        if(carrinho.getItens().isEmpty()){
            throw new RuntimeException("Inclua itens no carrinho");
        }

        FormaPagamento formaPagamento = numeroFormaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUININHA;
        carrinho.setFormaPagamento(formaPagamento);
        carrinho.setFechado(true);

        return carrinhoRepository.save(carrinho);
    }
}
