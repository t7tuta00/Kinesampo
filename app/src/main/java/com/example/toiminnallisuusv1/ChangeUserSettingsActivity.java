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
import com.android.volley.toolbox.Volley;

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

        editFirstName = findViewById(R.id.userNameEditText);
        editLastName = findViewById(R.id.passwordEditText);
        editAge = findViewById(R.id.ikaEditText);
        editWeight = findViewById(R.id.painoEditText);
        editLength = findViewById(R.id.pituusEditText);
        editCalories = findViewById(R.id.DayCaloriesEditText);

        mQueue = Volley.newRequestQueue(this);

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
        if (v.getId() == R.id.okButton)
        {
            Log.d(TAG, "\n"+ "onClick: "+id2+"\n");
            Firstname = editFirstName.getText().toString();
            Lastname = editLastName.getText().toString();
            CaloryGoal = editCalories.getText().toString();

            Log.d(TAG, "\n"+ "onClick: "+Firstname+"\n");
            Log.d(TAG, "\n"+ "onClick: "+Lastname+"\n");
            Log.d(TAG, "\n"+ "onClick: "+CaloryGoal+"\n");

            EditUser();
            EditCalories();

            Toast toast = Toast.makeText(getApplicationContext(), "Asetukset tallennettu", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void EditCalories()
    {
        String url = "http://ec2-35-172-199-159.compute-1.amazonaws.com/EditHealthKalorit?Kalorit="+ CaloryGoal +"&Kayttaja_idKayttaja="+id2;

       JsonArrayRequest request = new JsonArrayRequest(Request.Method.PUT, url, null,
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

    private void EditUser()
    {
        String url = "http://ec2-35-172-199-159.compute-1.amazonaws.com/EditKayttajaEtuSuku?Etunimi="+ Firstname +"&Sukunimi="+ Lastname +"&idKayttaja="+id2;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.PUT, url, null,
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
