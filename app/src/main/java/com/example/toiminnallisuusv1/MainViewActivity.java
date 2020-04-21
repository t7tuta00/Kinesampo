package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainViewActivity extends AppCompatActivity {

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        if (getIntent() != null)
        {
            id = getIntent().getIntExtra("id",0);
        }

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

        ProgressBar progressBar = findViewById(R.id.palkki);

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
            case R.id.action_account:
                return true;
            case R.id.subPasswordChange:
                startActivity(new Intent(getApplicationContext(), ChangePasswordActivity.class));
                return true;
            case R.id.subUserSettings:
                //startActivity(new Intent(getApplicationContext(), ChangeUserSettingsActivity.class));
                Intent intent = new Intent(this, ChangeUserSettingsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void health_intent(View view) {
        Intent intent = new Intent(this, HealthActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void food_intent(View view) {
        Intent intent = new Intent(this, FoodActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void milk_intent(View view) {
        Intent intent = new Intent(this, MilkActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void work_intent(View view) {
        Intent intent = new Intent(this, WorkActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void entertainment_intent(View view) {
        Intent intent = new Intent(this, EntertainmentActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void water_intent(View view) {
        Intent intent = new Intent(this, WaterActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void food_game_intent(View view) {
        Intent intent = new Intent(this, FoodGameActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void sport_game_intent(View view) {
        Intent intent = new Intent(this, ExerciseGameActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void toMainView(View view) {
        Intent mainViewIntent = new Intent(this, MainViewActivity.class);
        startActivity(mainViewIntent);
    }
}
