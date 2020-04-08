package com.example.toiminnallisuusv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class KaloriLaskinActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalori_laskin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.laskeButton) {
            View b = findViewById(R.id.clearButton);
            View c = findViewById(R.id.addButton);
            b.setVisibility(View.VISIBLE);
            c.setVisibility(View.VISIBLE);
        }

        else if (v.getId() == R.id.clearButton) {
            View b = findViewById(R.id.clearButton);
            View c = findViewById(R.id.addButton);
            b.setVisibility(View.INVISIBLE);
            c.setVisibility(View.INVISIBLE);
        }
    }
}