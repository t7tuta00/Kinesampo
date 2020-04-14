package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RuokaTietokantaActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruoka_tietokanta);

        Spinner kategoriaSpinner  = findViewById(R.id.kategoriatSpinner);
        String[] items = new String[]{"Hedelmät ja vihannekset", "Liha ja kala", "Valmisruoka", "Juomat", "Pakasteet", "Makeiset"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        kategoriaSpinner.setAdapter(adapter);

        findViewById(R.id.takaisinButton).setOnClickListener(this);
        findViewById(R.id.listaButton).setOnClickListener(this);
        findViewById(R.id.lisaaButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.takaisinButton) {
            Intent takaisinIntent = new Intent(RuokaTietokantaActivity.this, FoodActivity.class);
            startActivity(takaisinIntent);
        }
        else if (v.getId() == R.id.listaButton) {
            //tähän listaIntent
        }
        else if (v.getId() == R.id.lisaaButton) {
            //lisää tuotteen listalle
        }
    }
}