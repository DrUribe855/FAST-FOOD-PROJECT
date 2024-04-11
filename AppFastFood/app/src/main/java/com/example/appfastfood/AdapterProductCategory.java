package com.example.appfastfood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterProductCategory extends RecyclerView.Adapter<AdapterProductCategory.ViewHolder> {
    List<InfoProductsCategory> listProductsCategory;
    public AdapterProductCategory(List<InfoProductsCategory> listProductsCategory) {
        this.listProductsCategory = listProductsCategory;
    }
    @NonNull
    @Override
    public AdapterProductCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productos_categoys, parent, false);
        return new AdapterProductCategory.ViewHolder(vista);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterProductCategory.ViewHolder holder, int position) {
        InfoProductsCategory temporal = this.listProductsCategory.get(position);
        holder.cargarDatos(temporal);
    }
    @Override
    public int getItemCount() {
        return listProductsCategory.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name_product;
        TextView description_product;
        TextView price_product;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name_product = itemView.findViewById(R.id.name_product);
            this.description_product = itemView.findViewById(R.id.description_product);
            this.price_product = itemView.findViewById(R.id.price_product);

        }

        public void cargarDatos(InfoProductsCategory datos){
            this.name_product.setText(datos.getProduct_name());
            this.description_product.setText(datos.getDescription());
            this.price_product.setText(datos.getPrice());
        }
    }
}
