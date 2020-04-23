package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class GamesActivity extends AppCompatActivity {

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        if (getIntent() != null)
        {
            id = getIntent().getIntExtra("id",0);
        }

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
                startActivity(new Intent(getApplicationContext(), ChangeUserSettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    public void milk_intent(View view) {
        Intent intent = new Intent(this, MilkActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void toMainView(View view) {
        Intent mainViewIntent = new Intent(this, MainViewActivity.class);
        startActivity(mainViewIntent);
    }
}
