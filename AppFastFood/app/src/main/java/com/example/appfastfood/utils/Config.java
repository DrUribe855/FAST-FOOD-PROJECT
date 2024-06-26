package com.example.appfastfood.utils;

import android.content.Context;
import android.content.res.Resources;

import com.example.appfastfood.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    String apiHost;

    public Config (Context contexto){
        Resources res = contexto.getResources();
        InputStream inputStream = res.openRawResource(R.raw.config);

        // Leer las propiedades del archivo.

        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            apiHost = properties.getProperty("api_host");

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String getEndpoint(String endpoint){
        return  this.apiHost+endpoint;
    }

}
