package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FoodDatabaseActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_database);

        Spinner kategoriaSpinner  = findViewById(R.id.kategoriatSpinner);
        String[] items = new String[]{"Hedelmät ja vihannekset", "Liha ja kala", "Valmisruoka", "Juomat", "Pakasteet", "Makeiset"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        kategoriaSpinner.setAdapter(adapter);

        findViewById(R.id.listaButton).setOnClickListener(this);
        findViewById(R.id.lisaaButton).setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {if (v.getId() == R.id.listaButton) {
            //tähän listaIntent
        }
        else if (v.getId() == R.id.lisaaButton) {
            //lisää tuotteen listalle
        }
    }

    public void toMainView(View view) {
        Intent mainViewIntent = new Intent(this, MainViewActivity.class);
        startActivity(mainViewIntent);
    }
}
