package com.app.listadeclientes.helper;

import com.app.listadeclientes.model.Compra;

import java.util.List;

public interface ICompraDAO {

    public boolean salvar(Compra compra);
    public boolean atualizar(Compra compra);
    public  boolean deletar(Compra compra);
    public List<Compra> listar();
}
