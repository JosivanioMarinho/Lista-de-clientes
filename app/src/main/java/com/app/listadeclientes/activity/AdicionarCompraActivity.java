package com.app.listadeclientes.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CpuUsageInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.listadeclientes.R;
import com.app.listadeclientes.helper.CompraDAO;
import com.app.listadeclientes.model.Compra;

public class AdicionarCompraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_compra);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.botaoAdicionar :
                Toast.makeText(getApplicationContext(), "Adiconado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.botaoSalvar :

                CompraDAO compraDAO = new CompraDAO(getApplicationContext());

                Compra compra = new Compra();
                compra.setDescricao("Milho");
                compra.setQuantidade(5);
                compra.setPreco(70.0);
                compra.setValor(350.0);

                compraDAO.salvar(compra);

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
