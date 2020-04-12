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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button salasanaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salasanaButton = findViewById(R.id.signInButton);
        salasanaButton.setVisibility(View.GONE);

        final EditText salasanaEdit = (EditText) findViewById(R.id.password);
        salasanaEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                salasanaButton.setVisibility(View.VISIBLE);
                findViewById(R.id.textView).setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                salasanaButton.setVisibility(View.VISIBLE);
                findViewById(R.id.textView).setVisibility(View.GONE);
            }
        });
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
