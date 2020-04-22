package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Recipe extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        findViewById(R.id.takaisinButton).setOnClickListener(this);
        findViewById(R.id.ruokaButton1).setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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

    public void toMainView(View view) {
        Intent mainViewIntent = new Intent(this, MainViewActivity.class);
        startActivity(mainViewIntent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.takaisinButton) {
            Intent backIntent = new Intent(Recipe.this, FoodActivity.class);
            startActivity(backIntent);
        }
        else if (v.getId() == R.id.ruokaButton1) {
            Intent recipeIntent = new Intent(Recipe.this, Recipe1Activity.class);
            startActivity(recipeIntent);
        }
    }
}
