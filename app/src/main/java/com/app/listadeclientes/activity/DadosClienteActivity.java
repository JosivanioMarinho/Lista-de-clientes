package com.app.listadeclientes.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.listadeclientes.R;
import com.app.listadeclientes.adapter.AdapterListaCompras;
import com.app.listadeclientes.helper.CompraDAO;
import com.app.listadeclientes.helper.RecyclerItemClickListener;
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
    private Compra compraSelecionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_cliente);

        getSupportActionBar().setTitle("Dados do cliente");

        //recyclerView das compras
        recyclerListaCompras = findViewById(R.id.recyclerListaCompras);

        textNomeCliente     = findViewById(R.id.textNomeCliente);
        textEnderecoCliente = findViewById(R.id.textEnderecoCliente);
        textDataClientet    = findViewById(R.id.textDataCliente);
        textTelefoneCliente = findViewById(R.id.textTelefoneCliente);

        //Recupera os dados do cliente que estão na lista
        dadosCliente = (Cliente) getIntent().getSerializableExtra("dadosCliente");

        textNomeCliente.setText(dadosCliente.getNome());
        textEnderecoCliente.setText(dadosCliente.getEndereco());
        textDataClientet.setText(dadosCliente.getData());
        textTelefoneCliente.setText(dadosCliente.getTelefone());

        //Adicionar evento de click
        recyclerListaCompras.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerListaCompras,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Atualizar compra
                                Compra atualizarCompra = listaDeCompras.get(position);

                                //Enviar compra para tela de adicionar compra
                                Intent intent = new Intent(DadosClienteActivity.this, AdicionarCompraActivity.class);
                                intent.putExtra("atualizarCompra", atualizarCompra);

                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                //Deletar compra
                                compraSelecionada = listaDeCompras.get(position);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DadosClienteActivity.this);

                                alertDialog.setTitle("Confirmar exclusão");
                                alertDialog.setMessage("Deseja excluir os dados da compra "+ compraSelecionada.getDescricao() +" ?");

                                alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        CompraDAO compraDAO = new CompraDAO(getApplicationContext());
                                        if (compraDAO.deletar(compraSelecionada)){
                                            carregarListaCompras();
                                            Toast.makeText(getApplicationContext(), "Compra excluída com sucesso!",
                                                    Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(getApplicationContext(), "Erro ao excluir compra!",
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });

                                alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });

                                alertDialog.create();
                                alertDialog.show();

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                ));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_compra, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Intent telaAddCompra = new Intent(DadosClienteActivity.this, AdicionarCompraActivity.class);
        Cliente enviarID = dadosCliente;
        if (id == R.id.botaoAdicionarCompra){
            telaAddCompra.putExtra("enviarID", enviarID);
            startActivity(telaAddCompra);
        }

        return super.onOptionsItemSelected(item);
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
