package com.app.listadeclientes.model;

import java.io.Serializable;
import java.util.Date;

public class Cliente implements Serializable {

    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String data;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String emdereco, String telefone, String data) {
        this.id = id;
        this.nome = nome;
        this.endereco = emdereco;
        this.telefone = telefone;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String emdereco) {
        this.endereco = emdereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
