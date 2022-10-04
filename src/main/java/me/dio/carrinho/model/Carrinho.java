package me.dio.carrinho.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.carrinho.enumeration.FormaPagamento;

import javax.persistence.*;
import java.util.List;

// Com o Lombok podemos usar anotações que substituem o código que está comentado ao final, tornando o código mais limpo.
//Criar construtor com todos os atributos
@AllArgsConstructor

//Criar construtor sem atributos
@NoArgsConstructor

//Criar getters and setters
@Data

//Padrão que irá nos ajudar a construir o design pattern na camada de serviço
@Builder

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itens;
    private Double valorTotal;

    @Enumerated
    private FormaPagamento FormaPagamento;
    private Boolean fechado;

    /*
    // Por estarmos trabalhando com o hibernate, ele solicita também um construtor vazio
    public Carrinho(){
    }

    //construtor
    public Carrinho(long id, Cliente cliente, List<Item> itens, Double valorTotal, me.dio.carrinho.enumeration.FormaPagamento formaPagamento, boolean fechada) {
        this.id = id;
        this.cliente = cliente;
        this.itens = itens;
        this.valorTotal = valorTotal;
        FormaPagamento = formaPagamento;
        this.fechada = fechada;
    }

    //Métodos de auxilio

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public me.dio.carrinho.enumeration.FormaPagamento getFormaPagamento() {
        return FormaPagamento;
    }

    public void setFormaPagamento(me.dio.carrinho.enumeration.FormaPagamento formaPagamento) {
        FormaPagamento = formaPagamento;
    }

    public boolean isFechada() {
        return fechada;
    }

    public void setFechada(boolean fechada) {
        this.fechada = fechada;
    }*/
}
