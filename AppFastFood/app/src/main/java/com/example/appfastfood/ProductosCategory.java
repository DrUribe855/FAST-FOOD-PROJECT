package com.example.appfastfood;

import android.os.Bundle;
import android.widget.TextView;

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

public class ProductosCategory extends AppCompatActivity {
    List<InfoProductsCategory> listProductsCategory =  new ArrayList<>();
    RecyclerView recycler;
    TextView name_c;
    AdapterProductCategory adaptador = new AdapterProductCategory(listProductsCategory);
    String id_categoria, name_category;
    Config config;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_category);

        recycler = findViewById(R.id.recycler_products_categorys);
        name_c = findViewById(R.id.name_category);


        Bundle datos = getIntent().getExtras();
        id_categoria = datos.getString("category_id");
        name_category = datos.getString("category_name");
        name_c.setText("Categoria: " + name_category);
        System.out.println("Name" + name_category);
        config = new Config(getApplicationContext());
        extractProducts();
    }
    public void extractProducts(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = config.getEndpoint("API-FastFood/getCategoriaXProducto.php?id_categoria="+id_categoria);
        JsonObjectRequest solicitud =  new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("El servidor responde OK");
                System.out.println(response.toString());
                loadInformation(response);
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
    public void loadInformation(JSONObject data){
        try {
            JSONArray productsC = data.getJSONArray("respuesta");
            for (int i = 0; i < productsC.length(); i++) {
                JSONObject respuest = productsC.getJSONObject(i);
                String id_product = respuest.getString("id");
                String name = respuest.getString("product_name");
                String description = respuest.getString("description");
                String price = respuest.getString("price");
                String url = respuest.getString("image_url");

                System.out.println("ID de la producto: " + id_product);
                System.out.println("Nombre del producto: " + name);
                System.out.println("Descripcion " + description);
                System.out.println("Precio " + price);

                this.listProductsCategory.add(new InfoProductsCategory(id_product, name, price, url, description));
            }
                this.adaptador = new AdapterProductCategory(this.listProductsCategory);
                System.out.println("lista" + listProductsCategory);
                this.recycler.setAdapter(adaptador);
                this.recycler.setLayoutManager( new LinearLayoutManager(getApplicationContext()) );
        }catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
