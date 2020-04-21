package com.example.toiminnallisuusv1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeUserSettingsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    int id;
    String id2;
    String TAG="Testi";

    String Firstname;
    String Lastname;
    String Weight;
    String Calories;

    String Length;
    String Age;
    String Sleep = "Ei kaytossa";
    String CaloryGoal;

    EditText editFirstName;
    EditText editLastName;
    EditText editAge;
    EditText editWeight;
    EditText editLength;
    EditText editCalories;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_settings);
        findViewById(R.id.okButton).setOnClickListener(this);

        editFirstName = findViewById(R.id.etunimiEditText);
        editLastName = findViewById(R.id.sukunimiEditText);
        editAge = findViewById(R.id.ikaEditText);
        editWeight = findViewById(R.id.painoEditText);
        editLength = findViewById(R.id.pituusEditText);
        editCalories = findViewById(R.id.DayCaloriesEditText);

        if (getIntent() != null)
        {
            id = getIntent().getIntExtra("id",0);
        }

        id2 = Integer.toString(id);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        String[] categories = new String[] {
                "Sukupuoli", "Mies", "Nainen", "-" };

        final List<String> list = new ArrayList<>(Arrays.asList(categories));

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.okButton) {
            EditUser();
            Log.d(TAG, "onClick:!!!!!!!!!!!! "+id);
            Toast toast = Toast.makeText(getApplicationContext(), "Asetukset tallennettu", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void PostCalories()
    {
        //Weight
        //Calories

        //EditHealth PUT-metodi:
        //http://ec2-35-172-199-159.compute-1.amazonaws.com/EditHealth?Uni=10&Paino=90&Kalorit=2000&Paivan_tavoite=1800&Kayttaja_idKayttaja=1

        //EditKayttaja PUT-metodi
        //http://ec2-35-172-199-159.compute-1.amazonaws.com/
        // EditKayttaja?Kayttajanimi=Kilpipoika&Etunimi=poika&Sukunimi=pojanpoika&Salasana=poika&Sormenjalki=sormenjalki10&Sposti=poika@mail.com&idKayttaja=1

        //Otetaan paino ja kalorit talteen käyttäjältä ja tallennetaan ne post metodilla terveys-tauluun.
        //Kaloritavoitteen päivittäminen nollaa päivän kalorit.


        String url = "http://ec2-35-172-199-159.compute-1.amazonaws.com/addNewHealth?Uni="+ Sleep +"&Paino="+ Weight +"&Kalorit="+
                Calories +"&Kayttaja_idKayttaja="+ id2 +"&Paivan_tavoite="+ CaloryGoal;
    }

    private void EditUser() {

        //Otetaan Nimi ja sukunimi talteen ja päivitetään ne käyttäjätietoihin idKayttaja perusteella. Post metodi.

        EditText editText;
        String Firstname;
        String Lastname;

        String url = "";


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        Log.d(TAG, "onResponse: Onnistui");
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: Epäonnistui");
            }
        });

        mQueue.add(request);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
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

    public void toMainView(View view) {
        Intent mainViewIntent = new Intent(this, MainViewActivity.class);
        startActivity(mainViewIntent);
    }
}
