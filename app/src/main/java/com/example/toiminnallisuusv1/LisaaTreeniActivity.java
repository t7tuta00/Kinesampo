package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LisaaTreeniActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisaa_treeni);

        findViewById(R.id.takaisinButton).setOnClickListener(this);
        findViewById(R.id.tallennaButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.takaisinButton) {
            Intent takaisinIntent = new Intent(LisaaTreeniActivity.this, HealthActivity.class);
            startActivity(takaisinIntent);
        }
        else if (v.getId() == R.id.tallennaButton) {
            //tallenna treeni johonkin
        }
    }
}