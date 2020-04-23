package com.example.toiminnallisuusv1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CalorieCalculatorActivity extends AppCompatActivity implements View.OnKeyListener {

    Button add;
    TextView tulos, tulos2;
    EditText kcalEdit, amountEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calculator);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        add = findViewById(R.id.addButton);
        tulos = findViewById(R.id.tulosText);
        tulos2 = findViewById(R.id.tulosText2);
        kcalEdit = findViewById(R.id.calory);
        amountEdit = findViewById(R.id.amountEditText);

        amountEdit.setOnKeyListener(this);

        add.setVisibility(View.INVISIBLE);
        tulos2.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void laskeClick(View view) {
        String message = amountEdit.getText().toString();
        String message2 = kcalEdit.getText().toString();

        if (message.equals("") && message2.equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Syötä tarvittavat tiedot", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (message.equals("") || message2.equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Syötä tarvittavat tiedot", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            int eatenAmount = Integer.parseInt(message);
            int kcalAmount = Integer.parseInt(message2);
            int laskuTulos = kcalAmount * eatenAmount / 100;
            String tulosString = String.valueOf(laskuTulos);

            add.setVisibility(View.VISIBLE);
            tulos2.setVisibility(View.VISIBLE);
            tulos.setText(tulosString);
            kcalEdit.setText("");
            amountEdit.setText("");
        }
    }

    public void addClick(View view) {
        tulos.setText("");
        add.setVisibility(View.INVISIBLE);
        tulos2.setVisibility(View.INVISIBLE);
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
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN)
        {
            switch (keyCode)
            {
                case KeyEvent.KEYCODE_ENTER:
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    View passwordEdit = findViewById(R.id.amountEditText);
                    inputMethodManager.hideSoftInputFromWindow(passwordEdit.getWindowToken(), 0);
                    return true;
                default:
                    break;
            }
        }
        return false;
    }

    public void toMainView(View view) {
        Intent mainViewIntent = new Intent(this, MainViewActivity.class);
        startActivity(mainViewIntent);
    }
}