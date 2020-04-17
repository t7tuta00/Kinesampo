package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RequestQueue Queue;
    Button salasanaButton;
    TextView textView;
    ArrayList<Object> Userlist = new ArrayList<>();
    String Login;
    String Password;
    String CheckLogin="";
    String CheckPassword="";

    String msg = "Kayttis";
    String msg2 = "Salis";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salasanaButton = findViewById(R.id.signInButton);
        salasanaButton.setOnClickListener(this);
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
        Intent kirjauduIntent = new Intent(MainActivity.this, MainViewActivity.class);
        startActivity(kirjauduIntent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signInButton) {
            EditText asd = (EditText) findViewById(R.id.editText);
            EditText asd2 = (EditText) findViewById(R.id.password);
            Login = asd.getText().toString();
            Password = asd2.getText().toString();

            Queue = Volley.newRequestQueue(this);
            GetUser();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (CheckLogin.equals(Login) && CheckPassword.equals(Password))
                    {
                        Intent kirjauduIntent = new Intent(MainActivity.this, MainViewActivity.class);
                        startActivity(kirjauduIntent);
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Kirjautuminen epäonnistui, yrita uudelleen", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }, 1000);

        }
    }

    //Get-metodi, jolla tuodaan Kayttajatiedot tietokannasta Arraylistaan Userlist
    private void GetUser () {
        String url = "http://ec2-35-172-199-159.compute-1.amazonaws.com/login?Kayttajanimi=" + Login + "&Salasana=" + Password;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            //JSONArray jsonArrayalku = response.getJSONArray("areas")

                            JSONArray jsonArray = response;

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject o = jsonArray.getJSONObject(i);

                                    int id = o.getInt("idKayttaja");
                                    String username = o.getString("Kayttajanimi");
                                    String firstname = o.getString("Etunimi");
                                    String lastname = o.getString("Sukunimi");
                                    String password = o.getString("Salasana");
                                    String fingerprint = o.getString("Sormenjalki");
                                    String email = o.getString("Sposti");
                                    String addtime = o.getString("Lisaysaika");

                                    CheckLogin = username;
                                    CheckPassword = password;

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

        Queue.add(request);
    }
}

