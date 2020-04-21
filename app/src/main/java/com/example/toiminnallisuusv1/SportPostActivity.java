package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SportPostActivity extends AppCompatActivity implements View.OnClickListener{

    String TAG = "LOG: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
    Button button;
    int id;
    String id2;

    String SportName;
    String Calories;
    String Time;
    EditText sportText;
    EditText caloryText;
    EditText time;
    String Devices = "Ei maaritelty";
    String Sports = "Ei maaritelty";
    String Timer = "0";
    String Date = "Ei arvoa";
    //Date c = Calendar.getInstance().getTime();
    //SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
    //String formattedDate = df.format(c);

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_post);

        if (getIntent() != null)
        {
            id = getIntent().getIntExtra("id",0);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        button = findViewById(R.id.sendbtn);
        button.setOnClickListener(this);
        sportText = findViewById(R.id.nimi);
        caloryText = findViewById(R.id.calory);
        time = findViewById(R.id.time);
        mQueue = Volley.newRequestQueue(this);

        id2 = Integer.toString(id);
        Log.d(TAG, id+"?????????????????????????Check?????????????????????????????????");
        Log.d(TAG, id2+"?????????????????????????Check?????????????????????????????????");
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

    public void toMainView(View view) {
        Intent mainViewIntent = new Intent(this, MainViewActivity.class);
        startActivity(mainViewIntent);
    }

    public void post_sport() {

        SportName = sportText.getText().toString();
        Calories = caloryText.getText().toString();
        Time = time.getText().toString();
        String url = "http://ec2-35-172-199-159.compute-1.amazonaws.com/addNewSport?Timer=" + Timer + "&Poltetut_kalorit=" + Calories + "&Laitteet=" + Devices + "&Liikuntamuodot=" + Sports + "&Paivays=" + Date + "&TreeninNimi=" + SportName + "&Kesto=" + Time + "&Kayttaja_idKayttaja=" + id2;

        Log.d(TAG, SportName);
        Log.d(TAG, Calories);
        Log.d(TAG, Time);
        Log.d(TAG, id2);

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

    private void update_calories()
    {
        //haetaan nykyinen kalorimäärä tietokannasta, päivitetään se post_sport-metodin urheilukalorit lisäksi post metodilla.
    }

    @Override
    public void onClick (View v){
        if (v.getId() == R.id.sendbtn)
        {
            post_sport();
            update_calories();
        }
    }




}
