package com.app.listadeclientes.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.listadeclientes.R;
import com.app.listadeclientes.helper.ClienteDAO;
import com.app.listadeclientes.model.Cliente;
import com.app.listadeclientes.model.Compra;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarClienteActivity extends AppCompatActivity {

    private TextInputEditText inputNomeCliente;
    private TextInputEditText inputEnderecoCliente;
    private TextInputEditText inputDataCliente;
    private TextInputEditText inputTelefoneCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_cliente);

        getSupportActionBar().setTitle("Adicionar cliente");

        inputNomeCliente     = findViewById(R.id.inputNomeCliente);
        inputEnderecoCliente = findViewById(R.id.inputEnderecoCliente);
        inputDataCliente     = findViewById(R.id.inputDataCliente);
        inputTelefoneCliente = findViewById(R.id.inputTelefoneCliente);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_proximo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.botaoProximoMenu){

            String nome     = inputNomeCliente.getText().toString();
            String endereco = inputEnderecoCliente.getText().toString();
            String data     = inputDataCliente.getText().toString();
            String telefone = inputTelefoneCliente.getText().toString();

            Boolean camposValidados = verificarCampos(nome, endereco, data);

            ClienteDAO clienteDAO = new ClienteDAO(getApplicationContext());

            if(camposValidados){
                Cliente cliente = new Cliente();
                cliente.setNome(nome);
                cliente.setEndereco(endereco);
                cliente.setData(data);
                if (telefone == null || telefone.equals("")){
                    cliente.setTelefone("telefone");
                }else{cliente.setTelefone(telefone); }

                clienteDAO.salvar(cliente);

                finish();
            }else{
                Toast.makeText(getApplicationContext(),"Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean verificarCampos( String nome, String endereco, String data){

        Boolean camposValidados = true;

        if(nome == null || nome.equals("")){ camposValidados = false; }
        if(endereco == null || endereco.equals("")){ camposValidados = false; }
        if(data == null || data.equals("")){ camposValidados = false; }

        return camposValidados;
    }

}
