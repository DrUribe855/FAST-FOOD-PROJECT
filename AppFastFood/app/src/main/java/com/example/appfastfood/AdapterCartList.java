package com.example.appfastfood;

import android.content.Context;
import android.content.Intent;
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
    public static int subtotal_entero;


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
        holder.calculateSumProducts();
        holder.calculateSubtractionProducts();
    }

    @Override
    public int getItemCount() {
        return listaCartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct, imgBtnSubtract, imgBtnAdd;

        TextView nameProduct, cantProducts, price, subtotal;

        String textPriceUnit;

        Context contexto;

        int cantidadProducts = 1;

        int priceUnit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contexto = itemView.getContext();
            imgProduct = itemView.findViewById(R.id.imgProduct);
            imgBtnSubtract = itemView.findViewById(R.id.btnSubtract);
            imgBtnAdd = itemView.findViewById(R.id.btnAdd);
            nameProduct = itemView.findViewById(R.id.nameProduct);
            cantProducts = itemView.findViewById(R.id.cantProducts);
            price = itemView.findViewById(R.id.price);
            subtotal = itemView.findViewById(R.id.subtotal);

        }

        public void calculateSumProducts(){
            imgBtnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cantidadProducts+=1;
                    subtotal_entero = priceUnit*cantidadProducts;

                    System.out.println("Esta es la cantidad: "+cantidadProducts);
                    System.out.println("Este es el precio_unitario: "+priceUnit);
                    System.out.println("Este seria el subtotal: "+subtotal_entero);

                    cantProducts.setText(String.valueOf(cantidadProducts));
                    subtotal.setText(String.valueOf(subtotal_entero));

                }
            });
        }

        public void calculateSubtractionProducts(){
            imgBtnSubtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cantidadProducts-=1;
                    subtotal_entero = priceUnit*cantidadProducts;

                    System.out.println("Esta es la cantidad: "+cantidadProducts);
                    System.out.println("Este es el precio_unitario: "+priceUnit);
                    System.out.println("Este seria el total: "+subtotal_entero);

                    cantProducts.setText(String.valueOf(cantidadProducts));
                    subtotal.setText(String.valueOf(subtotal_entero));
                }
            });
        }

        public void mostrarDatosPrueba(CartList datos){

            nameProduct.setText(datos.getNameProduct());
            cantProducts.setText(datos.getCantProducts());
            price.setText(datos.getPrice());
            subtotal.setText(datos.getPrice());

            //Almacenamos el precio unitario del producto
            textPriceUnit = datos.getPrice();
            this.priceUnit = Integer.parseInt(textPriceUnit);

        }
    }
}
