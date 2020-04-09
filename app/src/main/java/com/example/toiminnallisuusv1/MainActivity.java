package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button salasanaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        salasanaButton = findViewById(R.id.signInButton);

        final EditText salasanaEdit = (EditText) findViewById(R.id.password);
        salasanaEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                salasanaButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                salasanaButton.setVisibility(View.VISIBLE);
            }
        });
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

    public void uusiKayttajaIntent(View view) {
        Intent uusiKayttajaIntent = new Intent(MainActivity.this, NewUserActivity.class);
        startActivity(uusiKayttajaIntent);
    }

    public void kirjauduIntent(View view) {
        Intent kirjauduIntent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(kirjauduIntent);
    }
}
