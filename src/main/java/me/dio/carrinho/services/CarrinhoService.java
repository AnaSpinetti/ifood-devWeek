package me.dio.carrinho.services;

import me.dio.carrinho.model.Carrinho;
import me.dio.carrinho.model.Item;
import me.dio.carrinho.resources.dto.ItemDto;

public interface CarrinhoService {
    Item incluirItemSacola(ItemDto itemDto);
    Carrinho verCarrinho(Long id);
    Carrinho fecharCarrinho(Long id, int formaPagamento);
}
