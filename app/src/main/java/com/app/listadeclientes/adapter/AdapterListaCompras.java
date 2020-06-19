package com.app.listadeclientes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.listadeclientes.R;
import com.app.listadeclientes.model.Compra;

import java.util.ArrayList;
import java.util.List;

public class AdapterListaCompras extends RecyclerView.Adapter<AdapterListaCompras.MyViewHolderCompras> {

    private List<Compra> listaCompras = new ArrayList<>();

    public AdapterListaCompras( List<Compra> listaCompras ) {
        this.listaCompras = listaCompras;
    }

    @NonNull
    @Override
    public MyViewHolderCompras onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View comprasLista = LayoutInflater.from(parent.getContext()).
                            inflate(R.layout.adapter_lista_compra, parent, false);

        return new MyViewHolderCompras(comprasLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderCompras holder, int position) {

        Compra compra = listaCompras.get(position);
        holder.descricaoComprasLista.setText(compra.getDescricao());
        holder.quantidadeComprasLista.setText(Integer.toString(compra.getQuantidade()));
        //String preco = String.valueOf(compra.getPreco());
        holder.precoComprasLista.setText("Preço: $ " + Double.toString(compra.getPreco()));
        //String valor = String.valueOf(compra.getValor());
        holder.totalComprasLista.setText("Valor Total: $ " + Double.toString(compra.getValor()));

    }

    @Override
    public int getItemCount() {
        return this.listaCompras.size();
    }

    public class MyViewHolderCompras extends RecyclerView.ViewHolder{

        TextView descricaoComprasLista;
        TextView quantidadeComprasLista;
        TextView precoComprasLista;
        TextView totalComprasLista;

        public MyViewHolderCompras(@NonNull View itemView) {
            super(itemView);

            descricaoComprasLista = itemView.findViewById(R.id.textDescricaoProduto);
            quantidadeComprasLista = itemView.findViewById(R.id.textQuantidadeProduto);
            precoComprasLista = itemView.findViewById(R.id.textPrecoProdutoLista);
            totalComprasLista = itemView.findViewById(R.id.textValorTotalProdutoLista);

        }
    }

}
