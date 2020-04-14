package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LisaaAteriaActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisaa_ateria);

        findViewById(R.id.takaisinButton).setOnClickListener(this);
        findViewById(R.id.tallennaButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.takaisinButton) {
            Intent takaisinIntent = new Intent(LisaaAteriaActivity.this, FoodActivity.class);
            startActivity(takaisinIntent);
        }
        if(v.getId() == R.id.tallennaButton) {
            // tallentaa aterian jonnekkin
        }
    }
}