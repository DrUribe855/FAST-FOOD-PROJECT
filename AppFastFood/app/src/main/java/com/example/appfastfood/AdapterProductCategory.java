package com.example.appfastfood;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

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
            String formattedPrice = String.valueOf(NumberFormat.getCurrencyInstance(new Locale("es", "CO")).format(Double.parseDouble(datos.getPrice())));
            SpannableString spannableString = new SpannableString(formattedPrice);
            int dollarSignIndex = formattedPrice.indexOf("$");
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#F87413")), dollarSignIndex, dollarSignIndex + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            this.price_product.setText(spannableString);
        }
    }
}
