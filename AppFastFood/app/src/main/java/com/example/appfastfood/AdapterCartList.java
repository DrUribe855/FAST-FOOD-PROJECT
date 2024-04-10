package com.example.appfastfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterCartList extends RecyclerView.Adapter < AdapterCartList.ViewHolder > {
    List<CartList> listaCartList;

    public AdapterCartList(List<CartList> lista){
        this.listaCartList = lista;
    }

    @NonNull
    @Override
    public AdapterCartList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_list, parent, false);
        return new ViewHolder(vista);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCartList.ViewHolder holder, int position) {

        CartList temporal = listaCartList.get(position);
        holder.mostrarDatosPrueba(temporal);
    }

    @Override
    public int getItemCount() {
        return listaCartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct, imgBtnSubtract, imgBtnAdd;

        TextView nameProduct, cantProducts, subtotal, total;

        Context contexto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contexto = itemView.getContext();
            imgProduct = itemView.findViewById(R.id.imgProduct);
            imgBtnSubtract = itemView.findViewById(R.id.btnSubtract);
            imgBtnAdd = itemView.findViewById(R.id.btnAdd);
            nameProduct = itemView.findViewById(R.id.nameProduct);
            cantProducts = itemView.findViewById(R.id.cantProducts);
            subtotal = itemView.findViewById(R.id.subtotal);
            total = itemView.findViewById(R.id.total);
        }

        public void mostrarDatosPrueba(CartList datos){

            nameProduct.setText(datos.getNameProduct());
            cantProducts.setText(datos.getCantProducts());
            subtotal.setText(datos.getSubtotal());
            total.setText(datos.getTotal());

        }
    }
}
