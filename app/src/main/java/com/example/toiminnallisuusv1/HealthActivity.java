package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HealthActivity extends AppCompatActivity implements View.OnClickListener{

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        if (getIntent() != null)
        {
            id = getIntent().getIntExtra("id",0);
        }

        findViewById(R.id.lisaaTreeni).setOnClickListener(this);
        findViewById(R.id.treeniButton).setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.lisaaTreeni) {
            Intent lisaaTreeniIntent = new Intent(HealthActivity.this, ExercisePostActivity.class);
            lisaaTreeniIntent.putExtra("id", id);
            startActivity(lisaaTreeniIntent);
        }
        else if (v.getId() == R.id.treeniButton) {
            Intent treenitIntent = new Intent(HealthActivity.this, ExerciseGetLayout.class);
            treenitIntent.putExtra("id", id);
            startActivity(treenitIntent);
        }
    }

    public void toMainView(View view) {
        Intent mainViewIntent = new Intent(this, MainViewActivity.class);
        startActivity(mainViewIntent);
    }
}