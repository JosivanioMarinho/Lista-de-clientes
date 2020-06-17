package com.app.listadeclientes.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.listadeclientes.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO{

    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;

    public ClienteDAO(Context context){
        BdHelper bdHelper = new BdHelper(context);
        escrever = bdHelper.getWritableDatabase();
        ler = bdHelper.getReadableDatabase();
    }


    @Override
    public boolean salvar(Cliente cliente) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", cliente.getNome());
        contentValues.put("endereco", cliente.getEndereco());
        contentValues.put("data", cliente.getData());
        contentValues.put("telefone", cliente.getTelefone());

        try{
            escrever.insert(BdHelper.TABELA_CLIENTE, null, contentValues );
            Log.i("tabela", "Cliente salvo com sucesso!");
        }catch (Exception e){
            Log.i( "tabela", "Erro ao inserir dados" + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Cliente cliente) {
        return false;
    }

    @Override
    public boolean deletar(Cliente cliente) {

        try{
            String[] args = {cliente.getId().toString()};
            escrever.delete(BdHelper.TABELA_CLIENTE, "id_cliente=?", args);
            Log.i("deletar", "Sucesso ao deletar");
        }
        catch (Exception e){
            Log.i("deletar", "Erro ao deletar" + e.getMessage());
            return false;
        }

        return  true;
    }

    @Override
    public List<Cliente> listar() {

        List<Cliente> cliente = new ArrayList<>();

        String sqlCliente = " SELECT * FROM " + BdHelper.TABELA_CLIENTE + " ; ";

        Cursor cursor = ler.rawQuery(sqlCliente, null);

        while ( cursor.moveToNext() ){

            Cliente  clienteLista = new Cliente();

            Long id         = cursor.getLong(cursor.getColumnIndex("id_cliente"));
            String nome     = cursor.getString(cursor.getColumnIndex("nome"));
            String endereco = cursor.getString(cursor.getColumnIndex("endereco"));
            String data     = cursor.getString(cursor.getColumnIndex("data"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));

            clienteLista.setId(id);
            clienteLista.setNome(nome);
            clienteLista.setEndereco(endereco);
            clienteLista.setData(data);
            clienteLista.setTelefone(telefone);

            cliente.add(clienteLista);
        }

        return cliente;
    }

}
