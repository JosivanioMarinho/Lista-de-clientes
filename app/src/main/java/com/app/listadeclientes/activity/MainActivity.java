package com.app.listadeclientes.activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.app.listadeclientes.R;
import com.app.listadeclientes.activity.AdicionarClienteActivity;
import com.app.listadeclientes.adapter.AdapterListaClientes;
import com.app.listadeclientes.helper.BdHelper;
import com.app.listadeclientes.helper.ClienteDAO;
import com.app.listadeclientes.helper.RecyclerItemClickListener;
import com.app.listadeclientes.model.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerListarClientes;
    private AdapterListaClientes adapterListaClientes;
    private List<Cliente> listaDeClientes = new ArrayList<>();
    private Cliente clienteSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Configurar recyclerView
        recyclerListarClientes = findViewById(R.id.recyclerListarClientes);

        //Adicionar evento de click
        recyclerListarClientes.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerListarClientes,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Ir para tela dos dados do cliente
                                Cliente dadosCliente = listaDeClientes.get(position);

                                Intent telaDadosCliente = new Intent(MainActivity.this, DadosClienteActivity.class);
                                telaDadosCliente.putExtra("dadosCliente", dadosCliente);

                                startActivity( telaDadosCliente );
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                //Deletar cliente
                                clienteSelecionado = listaDeClientes.get(position);

                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                                alertDialog.setTitle("Confirmar exclusão");
                                alertDialog.setMessage("Deseja excluir os dados do cliente "+ clienteSelecionado.getNome() +" ?");

                                alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        ClienteDAO clienteDAO = new ClienteDAO(getApplicationContext());
                                        if (clienteDAO.deletar(clienteSelecionado)){

                                            carregarListaClientes();
                                            Toast.makeText(getApplicationContext(), "Cliente excluído com sucesso!",
                                                                                    Toast.LENGTH_SHORT).show();

                                        }else{
                                            Toast.makeText(getApplicationContext(), "Erro ao excluir cliente!",
                                                                                    Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });

                                alertDialog.setNegativeButton("Não", null);

                                alertDialog.create();
                                alertDialog.show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), AdicionarClienteActivity.class);
                startActivity(intent);

            }
        });

    }

    public void carregarListaClientes(){

        //Listar os clientes
        ClienteDAO clienteDAO = new ClienteDAO(getApplicationContext());
        listaDeClientes = clienteDAO.listar();

        //Configurar Adapter
        adapterListaClientes = new AdapterListaClientes( listaDeClientes );

        /* Exibir a lista de clientes no recyclerView */

        //configurar o recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerListarClientes.setLayoutManager(layoutManager);
        recyclerListarClientes.setHasFixedSize(true);
        recyclerListarClientes.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerListarClientes.setAdapter(adapterListaClientes);

    }

    @Override
    protected void onStart() {
        carregarListaClientes();
        super.onStart();
    }

}
