package com.app.listadeclientes.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class BdHelper extends SQLiteOpenHelper {

    private static int VERSION = 8;
    private static String NOME_BD = "LISTA_CLIENTES";
    public static String TABELA_CLIENTE = "cliente";
    public static String TABELA_COMPRA = "compra";

    public BdHelper(Context context) {
        super(context, NOME_BD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCliente = " CREATE TABLE IF NOT EXISTS " + TABELA_CLIENTE
                    + " (id_cliente INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + " nome TEXT NOT NULL, "
                    + " endereco TEXT NOT NULL, "
                    + " data VARCHAR(10) NOT NULL, "
                    + " telefone VARCHAR(13) ); ";

        try{
            db.execSQL(sqlCliente);
            Log.i("infodobd", "Tabela de clientes criada");
        }catch(Exception e){
            Log.i("infodobd", "Erro ao criar as tabelas" + e.getMessage());
        }
    }

   /* @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if (!db.isReadOnly()){
            db.execSQL(" PRAGMA foreign_keys=ON ; ");
            Log.i("foreignkeys", "Chave estrangeira ativada");
        }
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlCompra = " CREATE TABLE IF NOT EXISTS " + TABELA_COMPRA
                + " (id_compra INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " descricao TEXT NOT NULL, "
                + " quantidade INTEGER NOT NULL, "
                + " preco FLOAT NOT NULL, "
                + " valor FLOAT NOT NULL, "
                + " cliente_id INTEGER NOT NULL, "
                + " FOREIGN KEY (cliente_id) REFERENCES " + TABELA_CLIENTE + " (id_cliente) "
                + " ); ";
        try {
            db.execSQL(sqlCompra);
            Log.i("tabelaCompra", "Sucesso ao CRIAR  tabela!" );
        }catch (Exception e){
            Log.i("tabelaCompra", "Erro ao CRIAR tabela " + e.getMessage());
        }
    }
}
