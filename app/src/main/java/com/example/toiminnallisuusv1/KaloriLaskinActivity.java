package com.example.toiminnallisuusv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class KaloriLaskinActivity extends AppCompatActivity {

    Button add, clear;
    TextView tulos;
    EditText kcalEdit, amountEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalori_laskin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        add = findViewById(R.id.addButton);
        clear = findViewById(R.id.clearButton);
        tulos = findViewById(R.id.tulosText);
        kcalEdit = findViewById(R.id.kcalEditText);
        amountEdit = findViewById(R.id.amountEditText);
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
        clear.setVisibility(View.VISIBLE);
        tulos.setText(tulosString);
    }

    public void clearClick(View view) {
        add.setVisibility(View.INVISIBLE);
        clear.setVisibility(View.INVISIBLE);
        tulos.setText("");
        kcalEdit.setText("");
        amountEdit.setText("");
    }

    public void addClick(View view) {
    }
}