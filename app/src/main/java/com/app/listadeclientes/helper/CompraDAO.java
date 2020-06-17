package com.app.listadeclientes.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.listadeclientes.model.Compra;

import java.util.List;

public class CompraDAO implements ICompraDAO {

    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;

    public CompraDAO(Context context) {
        BdHelper bd = new BdHelper(context);
        escrever = bd.getReadableDatabase();
        ler = bd.getWritableDatabase();
    }

    @Override
    public boolean salvar(Compra compra) {

        ContentValues contentValues = new ContentValues();


        try{
            //escrever.insert();
            Log.i("svcompra", "Ssucesso ao salvar compra!");
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
        return null;
    }
}
