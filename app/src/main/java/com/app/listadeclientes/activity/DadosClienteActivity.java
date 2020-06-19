package com.app.listadeclientes.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.listadeclientes.R;
import com.app.listadeclientes.adapter.AdapterListaCompras;
import com.app.listadeclientes.helper.CompraDAO;
import com.app.listadeclientes.model.Cliente;
import com.app.listadeclientes.model.Compra;

import java.util.ArrayList;
import java.util.List;

public class DadosClienteActivity extends AppCompatActivity {

    private TextView textNomeCliente;
    private TextView textEnderecoCliente;
    private TextView textDataClientet;
    private TextView textTelefoneCliente;

    private Cliente dadosCliente;
    private RecyclerView recyclerListaCompras;
    private AdapterListaCompras adapterListaCompras;
    private List<Compra> listaDeCompras = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_cliente);

        //recyclerView das compras
        recyclerListaCompras = findViewById(R.id.recyclerListaCompras);

        textNomeCliente     = findViewById(R.id.textNomeCliente);
        textEnderecoCliente = findViewById(R.id.textEnderecoCliente);
        textDataClientet    = findViewById(R.id.textDataCliente);
        textTelefoneCliente = findViewById(R.id.textTelefoneCliente);

        //Recupera os dados do cliente
        dadosCliente = (Cliente) getIntent().getSerializableExtra("dadosCliente");

        textNomeCliente.setText(dadosCliente.getNome());
        textEnderecoCliente.setText(dadosCliente.getEndereco());
        textDataClientet.setText(dadosCliente.getData());
        textTelefoneCliente.setText(dadosCliente.getTelefone());

    }

    public void carregarListaCompras(){

        //lista com os itens para o adapter
        CompraDAO compraDAO = new CompraDAO(getApplicationContext());
        listaDeCompras = compraDAO.listar();

        //Configurar Adapter
        adapterListaCompras = new AdapterListaCompras(listaDeCompras);

        //configurar recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerListaCompras.setLayoutManager(layoutManager);
        recyclerListaCompras.setHasFixedSize(true);
        recyclerListaCompras.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerListaCompras.setAdapter(adapterListaCompras);

    }

    @Override
    protected void onStart() {
        carregarListaCompras();
        super.onStart();
    }
}
