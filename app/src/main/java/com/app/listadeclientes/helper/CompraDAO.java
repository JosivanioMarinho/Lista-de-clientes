package com.app.listadeclientes.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.listadeclientes.model.Compra;

import java.util.ArrayList;
import java.util.List;

public class CompraDAO implements ICompraDAO {

    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;

    public CompraDAO(Context context) {
        BdHelper bd = new BdHelper(context);
        escrever = bd.getWritableDatabase();
        ler = bd.getReadableDatabase();
    }

    @Override
    public boolean salvar(Compra compra) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("descricao", compra.getDescricao());
        contentValues.put("quantidade", compra.getQuantidade());
        contentValues.put("preco", compra.getPreco());
        contentValues.put("valor", compra.getValor());

        try{
            escrever.insert(BdHelper.TABELA_COMPRA, null, contentValues);
            Log.i("svcompra", "Sucesso ao salvar compra!");
        }catch (Exception e){
            Log.i("svcompra", "Erro ao salvar compra " + e.getMessage());
        }

        return true;
    }

    @Override
    public boolean atualizar(Compra compra) {
        return false;
    }

    @Override
    public boolean deletar(Compra compra) {
        return false;
    }

    @Override
    public List<Compra> listar() {

        List<Compra> listaCompras = new ArrayList();

        String sql = " SELECT * FROM " + BdHelper.TABELA_COMPRA + " ;";

        Cursor cursor = ler.rawQuery(sql, null);

        while (cursor.moveToNext()){

            Long id             = cursor.getLong(cursor.getColumnIndex("id_compra"));
            String descricao    = cursor.getString(cursor.getColumnIndex("descricao"));
            Integer quantidade  = cursor.getInt(cursor.getColumnIndex("quantidade"));
            Double preco        = cursor.getDouble(cursor.getColumnIndex("preco"));
            Double valor        = cursor.getDouble(cursor.getColumnIndex("valor"));

            Compra compra = new Compra();

            compra.setId(id);
            compra.setDescricao(descricao);
            compra.setQuantidade(quantidade);
            compra.setPreco(preco);
            compra.setValor(valor);

            listaCompras.add(compra);
        }

        return listaCompras;
    }
}
