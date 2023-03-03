package com.example.conversordeunidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Temperatura extends AppCompatActivity {

    private EditText editText_1, editText_2;
    private boolean editText_1_IsFocus=false, editText_2_IsFocus=false;
    private String itemSelected_1="",itemSelected_2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        editText_1 = (EditText) findViewById(R.id.et_1);
        //Agregar el evento de onTextChanged
        editText_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editText_1.length()>0){
                        if (editText_1_IsFocus){
                            conversorLongitud(1);
                        }
                }else {
                    if (editText_1_IsFocus){
                        editText_1.setText("0");
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
        //Agregar el evento de onFocus
        editText_1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // El EditText ha obtenido el foco
                    editText_1_IsFocus=true;
                    editText_1.setBackgroundColor( getResources().getColor(R.color.gris_200));
                    //System.out.println("edit text 1 is focus");
                } else {
                    // El EditText ha perdido el foco
                    editText_1_IsFocus=false;
                    editText_1.setBackgroundColor( getResources().getColor(R.color.white));
                    //System.out.println("edit text 1 is NOT focus");
                }
            }
        });
        //this.editText_1=editText_1;

        editText_2 = (EditText) findViewById(R.id.et_2);
        //Agregar el evento de onTextChanged
        editText_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editText_2.length()>0){
                        if (editText_2_IsFocus){
                            conversorLongitud(2);
                        }
                }else {
                    if (editText_2_IsFocus){
                        editText_2.setText("0");
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
        //Agregar el evento de onFocus
        editText_2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // El EditText ha obtenido el foco
                    editText_2_IsFocus=true;
                    editText_2.setBackgroundColor( getResources().getColor(R.color.gris_200));
                    //System.out.println("edit text 2 is focus");
                } else {
                    // El EditText ha perdido el foco
                    editText_2_IsFocus=false;
                    editText_2.setBackgroundColor( getResources().getColor(R.color.white));
                    //System.out.println("edit text 2 is NOT focus");
                }
            }
        });
        //this.editText_2=editText_2;

        Spinner spinner_1 = (Spinner) findViewById(R.id.spinner_1);
        spinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String opcionSeleccionada = (String) parent.getItemAtPosition(position);
                itemSelected_1=opcionSeleccionada;
                if(editText_1.length()>0){
                        if (editText_1_IsFocus || editText_2_IsFocus){
                            conversorLongitud(1);
                        }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        Spinner spinner_2 = (Spinner) findViewById(R.id.spinner_2);
        spinner_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String opcionSeleccionada = (String) parent.getItemAtPosition(position);
                itemSelected_2=opcionSeleccionada;
                if(editText_2.length()>0 && editText_1.length()>0){
                        if (editText_1_IsFocus || editText_2_IsFocus){
                            conversorLongitud(2);
                        }
                }
                System.out.println("Spinner 2: "+opcionSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opciones_Temperatura, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adapter);
        spinner_2.setAdapter(adapter);

        editText_1.setText(QuitarPuntoCero(editText_1.getText().toString()));
        editText_2.setText(QuitarPuntoCero(editText_2.getText().toString()));
    }

    public void conversorLongitud(int et){

        double et_num1, et_num2;

        if (editText_1.length() == 0 || Double.isNaN(et_num1 = Double.parseDouble(editText_1.getText().toString())) || et_num1 == 0) {
            et_num1 = 0;
        } else if (TieneLetras(editText_1.getText().toString())) {
            editText_1.setText("");
            conversorLongitud(1);
            return;
        }

        if (editText_2.length() == 0 || Double.isNaN(et_num2 = Double.parseDouble(editText_2.getText().toString())) || et_num2 == 0) {
            et_num2 = 0;
        } else if (TieneLetras(editText_1.getText().toString())) {
            editText_2.setText("");
            conversorLongitud(2);
            return;
        }

        double res = 0;

        String itemSelected =  editText_1_IsFocus ? itemSelected_1 + itemSelected_2 : itemSelected_2 + itemSelected_1;

        res = (et == 1 && editText_1_IsFocus) || (et == 2 && !editText_2_IsFocus) ? et_num1 : et_num2;

        switch (itemSelected){
            case "CentigradosCentigrados":
            case "FahrenheitFahrenheit":
            case "KelvinKelvin":
                if (et == 1) {
                    res = editText_1_IsFocus ? et_num1 : et_num2;
                } else if (et == 2) {
                    res = editText_2_IsFocus ? et_num2 : et_num1;
                }
                break;

            case "CentigradosFahrenheit":
                res = (res * 1.8) + 32;
                break;

            case "CentigradosKelvin":
                        res = res + 273.15;
                break;

            case "KelvinCentigrados":
                res = res - 273.15;
                break;

            case "FahrenheitCentigrados":
                res = (res - 32) / 1.8;
                break;

            case "FahrenheitKelvin":
                res = (res - 32) / 1.8 + 273.15;
                break;

            case "KelvinFahrenheit":
                res = (res - 273.15) * 1.8 + 32;
                break;

            default:
                System.out.println("a");
                break;
        }

        EditText etFocused = editText_1;

        if (et == 1){
            etFocused = editText_1_IsFocus ? editText_2 : editText_1;
        }

        if (et == 2){
            etFocused = editText_2_IsFocus ? editText_1 : editText_2;
        }

        etFocused.setText(QuitarPuntoCero(String.valueOf(res)));

    }

    public void Reiniciar(View view){
        editText_1.setText("");
        editText_2.setText("");
    }

    public String QuitarPuntoCero(String num){
        return num.replaceFirst("\\.0$", "");
    }

    public boolean TieneLetras(String num){
        return num.matches(".*\\p{L}.*");
    }
}