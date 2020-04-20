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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FoodPostActivity extends AppCompatActivity implements View.OnClickListener{

    String TAG = "LOG: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
    Button button;
    int id;
    String id2;

    String FoodName;
    String Calories;
    String Fat;
    String Carbs;
    String Protein;
    EditText foodText;
    EditText caloryText;
    EditText fatText;
    EditText carbText;
    EditText proteinText;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_post);

        if (getIntent() != null)
        {
            id = getIntent().getIntExtra("id",0);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        button=findViewById(R.id.sendbtn);
        button.setOnClickListener(this);
        foodText = findViewById(R.id.nimi);
        caloryText =findViewById(R.id.calory);
        fatText =findViewById(R.id.fat);
        carbText =findViewById(R.id.carbs);
        proteinText = findViewById(R.id.protein);
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

   public void post_food() {
       MainActivity mainActivity = new MainActivity();

       //id2 = Integer.toString(id);
       FoodName = foodText.getText().toString();
       Calories = caloryText.getText().toString();
       Fat = fatText.getText().toString();
       Carbs = carbText.getText().toString();
       Protein = proteinText.getText().toString();
       String url = "http://ec2-35-172-199-159.compute-1.amazonaws.com/addNewFood?RuoanNimi=" + FoodName + "&Kalorit="
               + Calories + "&Rasva=" + Fat + "&Hiilihydraatit=" + Carbs + "&Proteiini="+ Protein + "&Kayttaja_idKayttaja=" + id2;

       Log.d(TAG, FoodName);
       Log.d(TAG, Calories);
       Log.d(TAG, Fat);
       Log.d(TAG, Carbs);
       Log.d(TAG, Protein);
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



    /*public void post_food(){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        MainActivity mainActivity = new MainActivity();
                        id = mainActivity.userid;
                        FoodName = foodText.getText().toString();
                        String calories = caloryText.toString();
                        Calories = Integer.parseInt(calories);
                        String fats = fatText.toString();
                        Fat = Integer.parseInt(fats);
                        String carbons = carbText.toString();
                        Carbs = Integer.parseInt(carbons);
                        String prots = proteinText.toString();
                        Protein = Integer.parseInt(prots);

                        try {
                            //JSONArray jsonArrayalku = response.getJSONArray("areas")

                            JSONArray jsonArray = response;

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject o = jsonArray.getJSONObject(i);

                                int id = o.getInt("idRuoka");
                                String food_name = o.getString("RuoanNimi");
                                int calories = o.getInt("Kalorit");
                                int fat = o.getInt("Rasva");
                                int carbonhydrates = o.getInt("Hiilihydraatit");
                                int protein = o.getInt("Proteiini");
                                int user = o.getInt("Kayttaja_idKayttaja");


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
    }*/


       @Override
       public void onClick (View v){
           if (v.getId() == R.id.sendbtn)
           {
               post_food();
               //Check();
           }
       }


   }




