package com.example.appfastfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CartListActivity extends AppCompatActivity {

    RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        List<CartList> listProducts = new ArrayList<>();
        listProducts.add(new CartList("Pizza","1","10,000","10,000"));
        listProducts.add(new CartList("Hamburguesa","2","15,000","30,000"));
        listProducts.add(new CartList("Perro","4","10,000","40,000"));

        recycler = findViewById(R.id.recycler_cart_list);

        AdapterCartList adapter = new AdapterCartList(listProducts);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager( new LinearLayoutManager(getApplicationContext()));
    }
}