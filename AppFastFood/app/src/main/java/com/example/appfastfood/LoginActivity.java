package com.example.appfastfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appfastfood.utils.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText campo_correo;
    EditText campo_password;
    Button btnIniciar;
    Config config;

    Button btn_Iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campo_correo = findViewById(R.id.campo_correo);
        campo_password = findViewById(R.id.campo_password);
        btnIniciar = findViewById(R.id.btnIniciar);
        config = new Config(getApplicationContext());
        // this.sesionValidate();
    }


    public void validarIngreso(View view){
        String email = campo_correo.getText().toString();
        String password = campo_password.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = config.getEndpoint("API-FastFood/validarIngreso.php");
        // System.out.println("URL de API: " + url);
        StringRequest solicitud =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("El servidor POST responde OK");
                    JSONObject jsonObject = new JSONObject(response);
                    System.out.println("Respuesta de API: " + jsonObject);
                    if (jsonObject.getBoolean("status")){
                        System.out.println(response);

                        String user_id = jsonObject.getJSONObject("user").getString("id");
                        String name = jsonObject.getJSONObject("user").getString("name");

                        changeActivity(user_id, name);
                    }else{
                        System.out.print("ERROR");

                    }


                } catch (JSONException e) {
                    System.out.println("El servidor POST responde con un error:");
                    System.out.println(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("El servidor POST responde con un error:");
                System.out.println(error.getMessage());
                System.out.println(error.toString());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        queue.add(solicitud);
    }

    public void changeActivity(String userId, String name){
        SharedPreferences file = getSharedPreferences("app-fastfood",MODE_PRIVATE);

        SharedPreferences.Editor editor = file.edit();

        editor.putString("userId", userId);
        editor.putString("name", name);

        editor.commit();

        Intent intention = new Intent(getApplicationContext(), Principal.class);
        startActivity(intention);
        finish();
    }

    public void sesionValidate(){
        SharedPreferences file = getSharedPreferences("app-fastfood", MODE_PRIVATE);
        String userId = file.getString("email", null);
        String name = file.getString("password", null);

        if(userId != null && name != null){
            Intent intention = new Intent(getApplicationContext(), Principal.class);
            startActivity(intention);
            finish();
        }
    }
}

