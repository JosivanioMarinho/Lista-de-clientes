package com.app.listadeclientes.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.listadeclientes.activity.AdicionarCompraActivity;
import com.app.listadeclientes.model.Cliente;
import com.app.listadeclientes.model.Compra;

import java.util.ArrayList;
import java.util.List;

public class CompraDAO implements ICompraDAO {

    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;

    private Long id;

    public CompraDAO(Context context, Long id) {
        BdHelper bd = new BdHelper(context);
        escrever = bd.getWritableDatabase();
        ler = bd.getReadableDatabase();
        this.id = id;
    }

    @Override
    public boolean salvar(Compra compra) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("descricao", compra.getDescricao());
        contentValues.put("quantidade", compra.getQuantidade());
        contentValues.put("preco", compra.getPreco());
        contentValues.put("valor", compra.getValor());
        contentValues.put("parcelas", compra.getQtdParcelas());
        contentValues.put("cliente_id", compra.getClient_id());

        try{
            escrever.insert(BdHelper.TABELA_COMPRA, null, contentValues);
            Log.i("svcompra", "Sucesso ao salvar compra!");
        }catch (Exception e){
            Log.i("svcompra", "Erro ao salvar compra " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Compra compra) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("descricao", compra.getDescricao());
        contentValues.put("quantidade", compra.getQuantidade());
        contentValues.put("preco", compra.getPreco());
        contentValues.put("valor", compra.getValor());
        contentValues.put("parcelas", compra.getQtdParcelas());

        try{
            String[] args = {compra.getId().toString()};
            escrever.update(BdHelper.TABELA_COMPRA, contentValues, "id_compra=?", args );
            Log.i("atualizar", "Sucesso ao atualizar compra!");
        }catch (Exception e){
            Log.i("atualizar", "Erro ao atualizar compra" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Compra compra) {

        try{
            String[] args = {compra.getId().toString()};
            escrever.delete(BdHelper.TABELA_COMPRA, "id_compra=?", args);
        }catch(Exception e){
            Log.i("deletarcompra", "Erro ao deletar " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<Compra> listar() {

        List<Compra> listaCompras = new ArrayList();
        Log.d("idDAO", "idDAO preenchido -----" + this.id);
        String sql = " SELECT * FROM compra WHERE compra.cliente_id="+this.id+";";
       // String sql = " SELECT * FROM " + BdHelper.TABELA_COMPRA + " ;";

        Cursor cursor = ler.rawQuery(sql, null);

        while (cursor.moveToNext()){

            Long id             = cursor.getLong(cursor.getColumnIndex("id_compra"));
            String descricao    = cursor.getString(cursor.getColumnIndex("descricao"));
            Integer quantidade  = cursor.getInt(cursor.getColumnIndex("quantidade"));
            Double preco        = cursor.getDouble(cursor.getColumnIndex("preco"));
            Double valor        = cursor.getDouble(cursor.getColumnIndex("valor"));
            Integer qtdParcelas = cursor.getInt(cursor.getColumnIndex("parcelas"));
            Long clienteID      = cursor.getLong(cursor.getColumnIndex("cliente_id"));

            Compra compra = new Compra();

            compra.setId(id);
            compra.setDescricao(descricao);
            compra.setQuantidade(quantidade);
            compra.setPreco(preco);
            compra.setValor(valor);
            compra.setQtdParcelas(qtdParcelas);
            compra.setClient_id(clienteID);

            listaCompras.add(compra);
        }

        return listaCompras;
    }

    public double valorTotal(){
        double total = 0;

        String sql = " SELECT * FROM compra WHERE compra.cliente_id="+this.id+";";

        Cursor cursor = ler.rawQuery(sql, null);

        while (cursor.moveToNext()){
            total += cursor.getDouble(cursor.getColumnIndex("valor"));
        }

        return total;
    }
}
