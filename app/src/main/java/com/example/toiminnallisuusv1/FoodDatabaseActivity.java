package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FoodDatabaseActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_database);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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
                return true;
            case R.id.subPasswordChange:
                startActivity(new Intent(getApplicationContext(), ChangePasswordActivity.class));
                return true;
            case R.id.subUserSettings:
                startActivity(new Intent(getApplicationContext(), ChangeUserSettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
