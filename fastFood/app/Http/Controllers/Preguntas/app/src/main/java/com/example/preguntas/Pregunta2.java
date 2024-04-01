package com.example.preguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Pregunta2 extends AppCompatActivity {
    Bundle datos;
    RadioGroup respuesta;
    String respuestaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta2);

        datos = getIntent().getExtras();

        respuesta = findViewById(R.id.respuesta2);

        respuesta.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);

            if (checkedId != -1) {
                respuestaSeleccionada = radioButton.getText().toString();
                System.out.println(respuestaSeleccionada);
                Toast.makeText(getApplicationContext(), "Seleccionaste: " + respuestaSeleccionada, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "No has seleccionado ninguna respuesta", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void responder(View vista) {
        if (respuesta.getCheckedRadioButtonId() != -1) {

            datos.putString("respuesta2", respuestaSeleccionada);


            Intent intencion = new Intent(this, Pregunta3.class);
            intencion.putExtras(datos);


            startActivity(intencion);

        } else {
            Toast.makeText(getApplicationContext(), "Por favor, selecciona una respuesta", Toast.LENGTH_SHORT).show();
        }
    }
}
