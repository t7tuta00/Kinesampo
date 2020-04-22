package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExerciseGetLayout extends AppCompatActivity {

    int id;
    private RequestQueue mQueue;
    ListView listView;
    TextView textView2;
    ArrayList<ArrayList> workouts;
    ArrayAdapter<ArrayList> adapter;
    TextView textview;
    int luku = 1;
    String id2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_get_layout);

        if (getIntent() != null)
        {
            id = getIntent().getIntExtra("id",0);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        id2 = Integer.toString(id);
        listView = findViewById(R.id.Liikuntalista);
        textview = findViewById(R.id.kentta);
        textview.setMovementMethod(new ScrollingMovementMethod());
        workouts = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);
        adapter = new ArrayAdapter<ArrayList>(this, android.R.layout.simple_list_item_1, workouts);
        listView.setAdapter(adapter);
        get_workout();
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

    public void get_workout(){
        String url = "http://ec2-35-172-199-159.compute-1.amazonaws.com/LiikuntaidKayttaja?Kayttaja_idKayttaja=" + id2;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            JSONArray jsonArray = response;

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject o = jsonArray.getJSONObject(i);

                                int id = o.getInt("idLiikunta");
                                String timer = o.getString("Timer");
                                int calories = o.getInt("Poltetut_kalorit");
                                String devices = o.getString("Laitteet");
                                String workoutStyle = o.getString("Liikuntamuodot");
                                String date = o.getString("Paivays");
                                String workoutName = o.getString("TreeninNimi");
                                String time = o.getString("Kesto");
                                int user = o.getInt("Kayttaja_idKayttaja");

                                textview.append("Aika: " + timer + "\n Kalorit: " + calories + "\n Laite: " + devices + "\nLiikuntamuoto: "
                                        + workoutStyle + "\nPaivamaara: " + date + "\n Treenin nimi: " + workoutName + "\n Kesto: " + time + " \n\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }


}
