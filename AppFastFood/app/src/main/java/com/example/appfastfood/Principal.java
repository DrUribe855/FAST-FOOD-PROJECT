package com.example.appfastfood;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appfastfood.utils.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Principal extends AppCompatActivity {
    List<Categorys>listaCategorys = new ArrayList<>();
    RecyclerView recycler;
    Config config;
    AdapterCategorys adaptador = new AdapterCategorys( this.listaCategorys );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        config = new Config(getApplicationContext());
        this.recycler = findViewById(R.id.recycler_categorys);
        getCategorys();
    }
    public void getCategorys(){
        System.out.println("Este soy");
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = config.getEndpoint("API-FastFood/getCategorias.php");
        JsonObjectRequest solicitud =  new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Respuesta correcta " +  response.toString());
                cargarDatos(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("El servidor responde con un error:");
                System.out.println(error.getMessage());
            }
        });
        queue.add(solicitud);
    }
    public void cargarDatos(JSONObject datos){
        try {
            JSONArray categoriesArray = datos.getJSONArray("categories");
            for (int i = 0; i < categoriesArray.length(); i++) {
                JSONObject respuest = categoriesArray.getJSONObject(i);
                String id_category = respuest.getString("id");
                String name = respuest.getString("category_name");

                System.out.println("ID de la categoría: " + id_category);
                System.out.println("Nombre de la categoría: " + name);

                this.listaCategorys.add(new Categorys(id_category, name));

            }
            this.adaptador = new AdapterCategorys(this.listaCategorys);
            System.out.println("lista" + listaCategorys);
            this.recycler.setAdapter(adaptador);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            this.recycler.setLayoutManager(layoutManager);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}












