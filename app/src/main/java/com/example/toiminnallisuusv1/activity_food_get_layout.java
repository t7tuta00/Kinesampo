package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class activity_food_get_layout extends AppCompatActivity implements View.OnClickListener {

    String id = "1";
    String url = "http://ec2-35-172-199-159.compute-1.amazonaws.com/RuokaidKayttaja?Kayttaja_idKayttaja=" + id;
    private RequestQueue mQueue;
    ListView listView;
    ArrayList<ArrayList> foods;
    ArrayAdapter<ArrayList> adapter;
    Button button;
    TextView textview;
    String result = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_food_get_layout);
        listView = findViewById(R.id.Ruokalista);
        button = findViewById(R.id.jsonbutton);
        button.setOnClickListener(this);
        textview = findViewById(R.id.kentta);
        textview.setMovementMethod(new ScrollingMovementMethod());
        foods = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);
        adapter = new ArrayAdapter<ArrayList>(this, android.R.layout.simple_list_item_1, foods);
        listView.setAdapter(adapter);
    }




    public void get_food(){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

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

                                /*Food_Data food_data = new Food_Data(id,food_name,calories,fat,carbonhydrates,protein,user);
                                foods.add(food_data);*/


                                textview.append("Ruoan nimi: " + food_name + "\n Kalorit: " + calories + "\nRasva: " + fat + "\nHiilihydraatit: "
                                        + carbonhydrates + "\nProteiinit: " + protein + " \n\n\n");
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.jsonbutton) {
            get_food();
        }
    }
}

