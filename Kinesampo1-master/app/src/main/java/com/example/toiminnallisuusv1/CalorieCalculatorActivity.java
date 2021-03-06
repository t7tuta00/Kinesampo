package com.example.toiminnallisuusv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CalorieCalculatorActivity extends AppCompatActivity {

    Button add;
    TextView tulos, tulos2;
    EditText kcalEdit, amountEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalori_laskin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        add = findViewById(R.id.addButton);
        tulos = findViewById(R.id.tulosText);
        tulos2 = findViewById(R.id.tulosText2);
        kcalEdit = findViewById(R.id.kcalEditText);
        amountEdit = findViewById(R.id.amountEditText);

        add.setVisibility(View.INVISIBLE);
        tulos2.setVisibility(View.INVISIBLE);
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

    public void takaisinIntent(View view) {
        Intent takaisinIntent = new Intent(this, FoodActivity.class);
        startActivity(takaisinIntent);
    }

    public void laskeClick(View view) {
        String message = amountEdit.getText().toString();
        int eatenAmount = Integer.parseInt(message);

        String message2 = kcalEdit.getText().toString();
        int kcalAmount = Integer.parseInt(message2);
        int laskuTulos = kcalAmount * eatenAmount / 100;
        String tulosString = String.valueOf(laskuTulos);

        add.setVisibility(View.VISIBLE);
        tulos2.setVisibility(View.VISIBLE);
        tulos.setText(tulosString);
        kcalEdit.setText("");
        amountEdit.setText("");
    }

    public void addClick(View view) {
        //täältä päivittyy tietokantaan, jotta näkyy lisättynä ruokanäkymään päivän kaloreihin
        tulos.setText("");
        add.setVisibility(View.INVISIBLE);
        tulos2.setVisibility(View.INVISIBLE);
    }
}