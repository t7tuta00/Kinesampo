package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Recipe extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        findViewById(R.id.takaisinButton).setOnClickListener(this);
        findViewById(R.id.ruokaButton1).setOnClickListener(this);
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
