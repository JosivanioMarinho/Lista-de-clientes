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
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarCompraActivity extends AppCompatActivity {

    private TextInputEditText inputDescricao;
    private TextInputEditText inputQuantidade;
    private TextInputEditText inputPreco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_compra);

        inputDescricao  = findViewById(R.id.inputDescricao);
        inputQuantidade = findViewById(R.id.inputQuantidade);
        inputPreco      = findViewById(R.id.inputPreco);

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

                String descricao  = inputDescricao.getText().toString();
                String quantidade = inputQuantidade.getText().toString();
                String preco      = inputPreco.getText().toString();
                Double total = Double.parseDouble(preco) * Integer.parseInt(quantidade);

                CompraDAO compraDAO = new CompraDAO(getApplicationContext());

                Compra compra = new Compra();
                compra.setDescricao(descricao);
                compra.setQuantidade(Integer.parseInt(quantidade));
                compra.setPreco(Double.parseDouble(preco));
                compra.setValor(total);

                compraDAO.salvar(compra);

                finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
