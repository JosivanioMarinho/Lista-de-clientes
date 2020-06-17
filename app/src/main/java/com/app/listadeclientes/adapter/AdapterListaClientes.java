package com.app.listadeclientes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.listadeclientes.R;
import com.app.listadeclientes.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class AdapterListaClientes extends RecyclerView.Adapter<AdapterListaClientes.MyViewHolderClientes> {

    List<Cliente> listaDeClientes = new ArrayList<>();

    public AdapterListaClientes(List<Cliente> listaClientes) {
        this.listaDeClientes = listaClientes;
    }

    @NonNull
    @Override
    public MyViewHolderClientes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View clienteLista = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.adapter_lista_cliente, parent, false);

        return new MyViewHolderClientes(clienteLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderClientes holder, int position) {

        Cliente cliente = listaDeClientes.get(position);
        holder.nomeClienteLista.setText(cliente.getNome());
        holder.enderecoClientelista.setText(cliente.getEndereco());

    }

    @Override
    public int getItemCount() {
        return this.listaDeClientes.size();
    }

    public class MyViewHolderClientes extends RecyclerView.ViewHolder {

        TextView nomeClienteLista;
        TextView enderecoClientelista;

        public MyViewHolderClientes(@NonNull View itemView) {
            super(itemView);

            nomeClienteLista     = itemView.findViewById(R.id.textNomeClienteLista);
            enderecoClientelista = itemView.findViewById(R.id.textEnderecoClienteList);
        }
    }

}
