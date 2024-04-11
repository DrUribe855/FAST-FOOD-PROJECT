package com.example.appfastfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.appfastfood.utils.Product;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartListActivity extends AppCompatActivity {

    RecyclerView recycler;

    int cantProducts = 1;

    Gson gson = new Gson();
    SharedPreferences sharedPreferences;
    List<CartList> listProducts = new ArrayList<>();

    AdapterCartList adapter = new AdapterCartList(listProducts);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        recycler = findViewById(R.id.recycler_cart_list);

        sharedPreferences = getSharedPreferences("product_list", MODE_PRIVATE);
        this.showCar();



    }

    public void showCar() {
        Set<String> stringSet = sharedPreferences.getStringSet("product_list", new HashSet<>());
        List<Product> productList = new ArrayList<>();

        for (String json : stringSet) {
            Product product = gson.fromJson(json, Product.class);
            productList.add(product);
        }

        for (Product product : productList) {
            listProducts.add(new CartList(product.getProduct_name(), "1", product.getPrice()));
        }

        adapter = new AdapterCartList(listProducts);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager( new LinearLayoutManager(getApplicationContext()));

        // Mostrar el subtotal_entero en un System.out
        System.out.println("Subtotal en CartListActivity: " + adapter.subtotal_entero);
    }


}


