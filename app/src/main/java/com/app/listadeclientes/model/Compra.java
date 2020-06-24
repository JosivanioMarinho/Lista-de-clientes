package com.app.listadeclientes.model;

import java.io.Serializable;

public class Compra implements Serializable {

    private Long id;
    private String descricao;
    private int quantidade;
    private double preco;
    private double valor;
    private Long client_id;

    public Compra() {
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Compra(Long id, String descricao, double preco, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
