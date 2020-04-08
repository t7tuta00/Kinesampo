package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageView kuva1 = findViewById(R.id.heart);
        kuva1.setImageResource(R.drawable.heart);

        ImageView ruoka = findViewById(R.id.ruokagame);
        ruoka.setImageResource(R.drawable.food);

        ImageView liikunta = findViewById(R.id.liikuntagame);
        liikunta.setImageResource(R.drawable.juoksu);

        ImageView maito = findViewById(R.id.maitogame);
        maito.setImageResource(R.drawable.milk);

        ImageView vesi = findViewById(R.id.vesigame);
        vesi.setImageResource(R.drawable.water);

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void health_intent(View view) {
        Intent intent = new Intent(this, HealthActivity.class);
        startActivity(intent);
    }

    public void food_intent(View view) {
        Intent intent = new Intent(this, FoodActivity.class);
        startActivity(intent);
    }

    public void work_intent(View view) {
        Intent intent = new Intent(this, WorkActivity.class);
        startActivity(intent);
    }

    public void entertainment_intent(View view) {
        Intent intent = new Intent(this, EntertainmentActivity.class);
        startActivity(intent);
    }

    public void water_intent(View view) {
        Intent intent = new Intent(this, VesiActivity.class);
        startActivity(intent);
    }

    public void food_game_intent(View view) {
        Intent intent = new Intent(this, VesiActivity.class);
        startActivity(intent);
    }

    public void sport_game_intent(View view) {
        Intent intent = new Intent(this, LiikuntaGameActivity.class);
        startActivity(intent);
    }




   /*@Override
    public void onClick(View v) {
        if(v.getId() == R.id.vesigame) {
            Intent intent = new Intent(this, VesiActivity.class);
            startActivity(intent);
        }
    }*/
}
