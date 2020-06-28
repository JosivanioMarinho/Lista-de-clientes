package com.app.listadeclientes.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CpuUsageInfo;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.listadeclientes.R;
import com.app.listadeclientes.helper.CompraDAO;
import com.app.listadeclientes.model.Cliente;
import com.app.listadeclientes.model.Compra;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarCompraActivity extends AppCompatActivity {

    private TextInputEditText inputDescricao;
    private TextInputEditText inputQuantidade;
    private TextInputEditText inputPreco;

    private Compra compraAtual;
    private Cliente clienteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_compra);

        inputDescricao  = findViewById(R.id.inputDescricao);
        inputQuantidade = findViewById(R.id.inputQuantidade);
        inputPreco      = findViewById(R.id.inputPreco);

        //Recuperar os dados da compra para atualiza-la
        compraAtual = (Compra) getIntent().getSerializableExtra("atualizarCompra");

        //Comfigurar compra nas caixas de texto
        if (compraAtual != null){
            inputDescricao.setText(compraAtual.getDescricao());
            inputQuantidade.setText(String.valueOf(compraAtual.getQuantidade()));
            inputPreco.setText(String.valueOf(compraAtual.getPreco()));

            //Mudar título da toobar caso seja edição da compra
            getSupportActionBar().setTitle("Atualizar dados da compra");
        }else{
            //caso não seja atualização, será adição de uma compra
            getSupportActionBar().setTitle("Adicionar compra");
        }

        //Configurando id do cliente para listagem de compras
        clienteID = (Cliente) getIntent().getSerializableExtra("enviarID");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.botaoSalvar :

                CompraDAO compraDAO = new CompraDAO(getApplicationContext(), null);

                if( compraAtual != null ){ //Atualizar
                    String descricao  = inputDescricao.getText().toString();
                    String quantidade = inputQuantidade.getText().toString();
                    String preco      = inputPreco.getText().toString();
                    Double total      = 0.0;

                    boolean camposValidados = validarCampos(descricao, quantidade, preco, total);

                    if (camposValidados){
                        Compra compraAtualizar = new Compra();
                        compraAtualizar.setId(compraAtual.getId());
                        compraAtualizar.setDescricao(descricao);
                        compraAtualizar.setQuantidade(Integer.parseInt(quantidade));
                        compraAtualizar.setPreco(Double.parseDouble(preco));
                        total = Integer.parseInt(quantidade) * Double.parseDouble(preco);
                        compraAtualizar.setValor(total);

                        //Atualizar banco de dados
                        if (compraDAO.atualizar(compraAtualizar)){
                            finish();
                            Toast.makeText(getApplicationContext(), "Compra atualizada!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Erro ao atualizr compra!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Preencha os campos", Toast.LENGTH_SHORT).show();
                    }

                }else{// Salvar
                    String descricao  = inputDescricao.getText().toString();
                    String quantidade = inputQuantidade.getText().toString();
                    String preco      = inputPreco.getText().toString();
                    Double total      = 0.0;
                    Long cID          = clienteID.getId();
                    Log.d("cID", " ID do cliente --- " + cID);

                    boolean camposValidados = validarCampos(descricao, quantidade, preco, total);

                    if (camposValidados){
                        Compra compra = new Compra();

                        compra.setDescricao(descricao);
                        compra.setQuantidade(Integer.parseInt(quantidade));
                        compra.setPreco(Double.parseDouble(preco));
                        total = Integer.parseInt(quantidade) * Double.parseDouble(preco);
                        compra.setValor(total);
                        compra.setClient_id(cID);

                        if (compraDAO.salvar(compra)){
                            finish();
                            Toast.makeText(getApplicationContext(), "Compra salva com sucesso!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Erro ao salvar compra", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Preencha os campos", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean validarCampos(String descricao, String quantidade, String preco, Double total){

        boolean campos = true;

        if (descricao == null || descricao.equals("")){campos = false;}
        if (quantidade == null || quantidade.equals("")){campos = false;}
        if (preco == null || preco.equals("")){campos = false;}
        if (total == null || total.equals("")){campos = false;}

        return campos;
    }
}
