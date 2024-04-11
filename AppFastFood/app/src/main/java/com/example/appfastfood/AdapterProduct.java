package com.example.appfastfood;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfastfood.utils.Product;

import java.util.ArrayList;
import java.util.List;

import java.text.NumberFormat;
import java.util.Locale;
public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {

    List<Product> listProduct;
    List<Product> carList;

    public AdapterProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
        this.carList = new ArrayList<>();
    }

    @NonNull
    @Override
    public AdapterProduct.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduct.ViewHolder holder, int position) {
        Product temp = this.listProduct.get(position);
        holder.loadData(temp);
    }

    @Override
    public int getItemCount() {
        return this.listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView producName, descriptio, price;
        ImageView producImg;
        Button btnAddCarr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            producName = itemView.findViewById(R.id.product_name);
            descriptio = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            producImg = itemView.findViewById(R.id.product_img);
            btnAddCarr = itemView.findViewById(R.id.btn_addCar);

            btnAddCarr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Product product = listProduct.get(position);
                        addProductToList(product);
                    }
                }
            });
        }

        public void loadData(Product data) {
            producName.setText(data.getProduct_name());
            descriptio.setText(data.getDescription());
            String formattedPrice = String.valueOf(NumberFormat.getCurrencyInstance(new Locale("es", "CO")).format(Double.parseDouble(data.getPrice())));
            SpannableString spannableString = new SpannableString(formattedPrice);
            int dollarSignIndex = formattedPrice.indexOf("$");
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#F87413")), dollarSignIndex, dollarSignIndex + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            price.setText(spannableString);
        }

        private void addProductToList(Product product) {
            carList.add(product);
        }
    }
}

