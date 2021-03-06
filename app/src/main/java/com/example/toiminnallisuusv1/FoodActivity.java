package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    int id;
    EditText addKcal;
    TextView dayKcal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        if (getIntent() != null)
        {
            id = getIntent().getIntExtra("id",0);
        }

        findViewById(R.id.ruokaTietokanta).setOnClickListener(this);
        findViewById(R.id.reseptit).setOnClickListener(this);
        findViewById(R.id.kaikkienruuat).setOnClickListener(this);
        findViewById(R.id.foodpost).setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        dayKcal = findViewById(R.id.dayKcal);
        addKcal = findViewById(R.id.addKcal);
        addKcal.setOnKeyListener(this);
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
        if (v.getId()==R.id.ruokaTietokanta) {
            Intent tietokantaIntent = new Intent (FoodActivity.this, FoodDatabaseActivity.class);
            tietokantaIntent.putExtra("id", id);
            startActivity(tietokantaIntent);
        }
        else if (v.getId() == R.id.reseptit) {
            Intent reseptitIntent = new Intent(FoodActivity.this, Recipe.class);
            reseptitIntent.putExtra("id", id);
            startActivity(reseptitIntent);
        }
        else if (v.getId() == R.id.kaikkienruuat) {
            Intent everybodysIntent = new Intent(FoodActivity.this, activity_food_get_layout.class);
            everybodysIntent.putExtra("id", id);
            startActivity(everybodysIntent);
        }
        else if (v.getId() == R.id.foodpost) {
            Intent everybodysIntent = new Intent(FoodActivity.this, FoodPostActivity.class);
            everybodysIntent.putExtra("id", id);
            startActivity(everybodysIntent);
        }
    }

    public void kalorilaskin_intent(View view) {
        Intent laskinIntent = new Intent(this, CalorieCalculatorActivity.class);
        startActivity(laskinIntent);
    }

    public void calculateFunction() {
        String message = addKcal.getText().toString();

        if (message.equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Lisää syötettävät kalorit", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            String message2 = dayKcal.getText().toString();

            int eatenAmount = Integer.parseInt(message);
            int eatenAmount2 = Integer.parseInt(message2);

            int finalAmount = eatenAmount + eatenAmount2;
            String tulosString = String.valueOf(finalAmount);
            dayKcal.setText(tulosString);
            addKcal.setText("");
        }
    }

    public void kalorilaskuri_intent(View view) {
        calculateFunction();

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View passwordEdit = findViewById(R.id.addKcal);
        inputMethodManager.hideSoftInputFromWindow(passwordEdit.getWindowToken(), 0);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN)
        {
            switch (keyCode)
            {
                case KeyEvent.KEYCODE_ENTER:
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    View passwordEdit = findViewById(R.id.addKcal);
                    inputMethodManager.hideSoftInputFromWindow(passwordEdit.getWindowToken(), 0);
                    calculateFunction();
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