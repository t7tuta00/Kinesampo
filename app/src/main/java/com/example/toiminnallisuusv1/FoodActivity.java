package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener {

    EditText addKcal;
    TextView dayKcal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        findViewById(R.id.takaisinButton).setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        addKcal = findViewById(R.id.addKcal);
        dayKcal = findViewById(R.id.dayKcal);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miLogOut:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
            case R.id.action_tili:
                startActivity(new Intent(getApplicationContext(), TiliAsetuksetActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.takaisinButton) {
            Intent takaisinIntent = new Intent(FoodActivity.this, MenuActivity.class);
            startActivity(takaisinIntent);
        }
    }

    public void kalorilaskin_intent(View view) {
        Intent laskinIntent = new Intent(this, KaloriLaskinActivity.class);
        startActivity(laskinIntent);
    }

    public void kalorilaskuri_intent(View view) {
        String message = addKcal.getText().toString();
        int eatenAmount = Integer.parseInt(message);
        String message2 = dayKcal.getText().toString();
        int eatenAmount2 = Integer.parseInt(message2);

        int finalAmount = eatenAmount + eatenAmount2;
        String tulosString = String.valueOf(finalAmount);
        dayKcal.setText(tulosString);
        addKcal.setText("");
    }
}