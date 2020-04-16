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
        findViewById(R.id.ruokaTietokanta).setOnClickListener(this);
        findViewById(R.id.lisaaAteria).setOnClickListener(this);
        findViewById(R.id.reseptit).setOnClickListener(this);
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
            case R.id.action_account:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
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
        else if (v.getId()==R.id.ruokaTietokanta) {
            Intent tietokantaIntent = new Intent (FoodActivity.this, FoodDatabaseActivity.class);
            startActivity(tietokantaIntent);
        }
        else if (v.getId()==R.id.lisaaAteria) {
            Intent lisaaAteriaIntent = new Intent (FoodActivity.this, AddMealActivity.class);
            startActivity(lisaaAteriaIntent);
        }
        else if (v.getId() == R.id.reseptit) {
            Intent reseptitIntent = new Intent(FoodActivity.this, Recipe.class);
            startActivity(reseptitIntent);
        }
    }

    public void kalorilaskin_intent(View view) {
        Intent laskinIntent = new Intent(this, CalorieCalculatorActivity.class);
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