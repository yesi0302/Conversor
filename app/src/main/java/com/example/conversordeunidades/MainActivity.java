package com.example.conversordeunidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void vistaLongitud(View view) {
        Intent vista = new Intent(this, Longitud.class);
        startActivity(vista);
    }

    public void vistaPeso(View view) {
        Intent vista = new Intent(this, Peso.class);
        startActivity(vista);
    }

    public void vistaVolumen(View view) {
        Intent vista = new Intent(this, Volumen.class);
        startActivity(vista);
    }

    public void vistaTemperatura(View view) {
            Intent vista = new Intent(this, Temperatura.class);
        startActivity(vista);
    }
}