package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.String.valueOf;

public class MainViewActivity extends AppCompatActivity implements View.OnClickListener{

    String TAG = "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
    int id;
    int progress = 0;
    private RequestQueue mQueue;
    Button button;
    String Timer;
    int burntCalories;
    ProgressBar simpleProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        if (getIntent() != null)
        {
            id = getIntent().getIntExtra("id",0);
        }

        ProgressBar progressBar = findViewById(R.id.palkki);

        button = findViewById(R.id.nappi);
        button.setOnClickListener(this);

        simpleProgressBar=(ProgressBar)findViewById(R.id.palkki);
        simpleProgressBar.setMax(60);
        mQueue = Volley.newRequestQueue(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        simpleProgressBar.setProgress(progress);
        Log.d(TAG, valueOf(progress));
        //Log.d(TAG, time);
        Log.d(TAG, String.valueOf(progress));
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
                //startActivity(new Intent(getApplicationContext(), ChangeUserSettingsActivity.class));
                Intent intent = new Intent(this, ChangeUserSettingsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void get_time() {
        Log.d(TAG, "get_time: Aloitetaan");
        String url = "http://ec2-35-172-199-159.compute-1.amazonaws.com/LiikuntaidKayttaja?Kayttaja_idKayttaja=" + id;
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

                                Timer = time;
                                burntCalories = calories;

                                int Days = Integer.parseInt(time.substring(0,2)); //days
                                int Hours = Integer.parseInt(time.substring(3,5)); //Hours
                                int Minutes = Integer.parseInt(time.substring(6,8)); //Minutes

                                Days = (Days*24)*60;
                                Hours = Hours*60;
                                Minutes = Minutes+Hours+Days;

                                progress = Minutes;

                                simpleProgressBar.setProgress(progress);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onResponse: error");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);

        new CountDownTimer(1750, 1750) {
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                Log.d(TAG, "get_time: lisätään");
                Log.d(TAG, "!!!!!!!!!!!!!!!!::::" + valueOf(progress));
                Log.d(TAG, "get_time: lisätään"+Timer);
                Log.d(TAG, "get_time: lisätään"+burntCalories);
            }
        }.start();
    }

    public void health_intent(View view) {
        Intent intent = new Intent(this, HealthActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void food_intent(View view) {
        Intent intent = new Intent(this, FoodActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void milk_intent(View view) {
        Intent intent = new Intent(this, MilkActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void work_intent(View view) {
        Intent intent = new Intent(this, WorkActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void entertainment_intent(View view) {
        Intent intent = new Intent(this, EntertainmentActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void water_intent(View view) {
        Intent intent = new Intent(this, WaterActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void food_game_intent(View view) {
        Intent intent = new Intent(this, FoodGameActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void sport_game_intent(View view) {
        Intent intent = new Intent(this, ExerciseGameActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void toMainView(View view) {
        Intent mainViewIntent = new Intent(this, MainViewActivity.class);
        startActivity(mainViewIntent);
    }
    public void shop_intent(View view) {
        Intent intent = new Intent(this, ShopActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nappi) {
            get_time();
        }
    }
}
