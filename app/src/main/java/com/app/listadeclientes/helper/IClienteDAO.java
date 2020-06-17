package com.app.listadeclientes.helper;

import com.app.listadeclientes.model.Cliente;

import java.util.List;

public interface IClienteDAO {

    public boolean salvar(Cliente cliente);
    public boolean atualizar(Cliente cliente);
    public boolean deletar(Cliente cliente);
    public List<Cliente> listar();
}
