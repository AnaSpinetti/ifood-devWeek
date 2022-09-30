package me.dio.carrinho.resources;

import lombok.RequiredArgsConstructor;
import me.dio.carrinho.model.Carrinho;
import me.dio.carrinho.model.Item;
import me.dio.carrinho.resources.dto.ItemDto;
import me.dio.carrinho.services.CarrinhoService;
import org.springframework.web.bind.annotation.*;

//Para indicar que é uma classe onde terá nossos endpoints
@RestController
//Definindo o endpoint
@RequestMapping("/ifood-devweek/carrinho")

// cria uma classe construtora para o nosso private final
@RequiredArgsConstructor
public class CarrinhoResource {

    private final CarrinhoService carrinhoService;

    @PostMapping
    public Item incluirItemSacola(@RequestBody ItemDto itemDto){
    return carrinhoService.incluirItemSacola(itemDto);
    }

    @GetMapping("/{id}")
    public Carrinho verCarrinho(@PathVariable("id") Long id){
        return carrinhoService.verCarrinho(id);
    }

    @PatchMapping("/fecharCarrinho/{idCarrinho}")
    public Carrinho fecharCarrinho(@PathVariable("idCarrinho") Long idCarrinho, @RequestParam("formaPagamento") int formaPagamento){
        return carrinhoService.fecharCarrinho(idCarrinho, formaPagamento);
    }
}
