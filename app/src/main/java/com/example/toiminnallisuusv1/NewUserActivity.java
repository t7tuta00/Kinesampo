package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class NewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.takaisinButton) {
            Intent takaisinIntent = new Intent(NewUserActivity.this, MainActivity.class);
            startActivity(takaisinIntent);
        }
        else if (v.getId() == R.id.okButton) {
            Intent okIntent = new Intent(NewUserActivity.this, MainViewActivity.class);
            startActivity(okIntent);
        }
    }

    public void takaisinIntent(View view) {
        Intent takaisinIntent = new Intent(NewUserActivity.this, MainActivity.class);
        startActivity(takaisinIntent);
    }

    public void seuraavaIntent(View view) {
        Intent takaisinIntent = new Intent(NewUserActivity.this, NewUserActivity2.class);
        startActivity(takaisinIntent);
    }
}
