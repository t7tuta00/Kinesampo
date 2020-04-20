package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    private RequestQueue Queue;
    Button salasanaButton;
    TextView textView;
    ArrayList<Object> Userlist = new ArrayList<>();

    int userid;
    String Login = "Empty Value";
    String Password = "Empty Value";
    String CheckLogin = "Empty Value";
    String CheckPassword = "Empty Value";
    String FirstName = "Empty Value";
    String LastName = "Empty Value";
    String Fingerprint = "Empty Value";
    String Email = "Empty Value";
    String Addtime = "Empty Value";

    String msg = "Kayttis";
    String msg2 = "Salis";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salasanaButton = findViewById(R.id.signInButton);
        salasanaButton.setOnClickListener(this);
        salasanaButton.setVisibility(View.GONE);

        final EditText usernameEdit = findViewById(R.id.username);
        final EditText passwordEdit = (EditText) findViewById(R.id.password);

        passwordEdit.setOnKeyListener(this);
        usernameEdit.setOnKeyListener(this);
        passwordEdit.addTextChangedListener(new TextWatcher() {

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

    public void signInFunction() {
        EditText asd = (EditText) findViewById(R.id.username);
        EditText asd2 = (EditText) findViewById(R.id.password);

        salasanaButton.setEnabled(false);
        salasanaButton.setBackgroundResource(R.drawable.buttonshapedisabled);

        Login = asd.getText().toString();
        Password = asd2.getText().toString();

        Queue = Volley.newRequestQueue(this);

        GetUser();

        if (Password.equals("") && Login.equals("")) {
        }
        else if (Password.equals("") || Login.equals("")) {
        }
        else if (CheckLogin.equals(Login) && CheckPassword.equals(Password)) {
            Check();
            Log.d(msg, "run: "+ Login + Password);
            Intent kirjauduIntent = new Intent(MainActivity.this, MainViewActivity.class);
            kirjauduIntent.putExtra("id", userid);
            startActivity(kirjauduIntent);
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Virheellinen käyttäjätunnus tai salasana", Toast.LENGTH_SHORT);
            toast.show();
        }

        new CountDownTimer(2000, 2000) {

            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                salasanaButton.setEnabled(true);
                salasanaButton.setBackgroundResource(R.drawable.buttonshape0);
            }
        }.start();

        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (Password.equals(null) && Login.equals(null)) {
                }
                else if (Password.equals(null) || Login.equals(null)) {
                }
                Check();
                Log.d(msg, "run: "+ Login + Password);

                if (CheckLogin.equals(Login) && CheckPassword.equals(Password))
                {
                    Intent kirjauduIntent = new Intent(MainActivity.this, MainViewActivity.class);
                    kirjauduIntent.putExtra("id", userid);
                    startActivity(kirjauduIntent);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Virheellinen käyttäjätunnus tai salasana", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }, 2000);*/
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signInButton) {
            signInFunction();
        }
    }

    private void Check(){
        Log.d(msg2, "id" +userid +"!!!!!!!!!!!!!!!!!!!!!!!!onClick!!!!!!!!!!!!!!!!!!!!!");
        Log.d(msg2, "id" +CheckLogin +"!!!!!!!!!!!!!!!!!!!!!!!!onClick!!!!!!!!!!!!!!!!!!!!!");
        Log.d(msg2, "id" +CheckPassword +"!!!!!!!!!!!!!!!!!!!!!!!!onClick!!!!!!!!!!!!!!!!!!!!!");
        Log.d(msg2, "id" +FirstName +"!!!!!!!!!!!!!!!!!!!!!!!!onClick!!!!!!!!!!!!!!!!!!!!!");
        Log.d(msg2, "id" +LastName +"!!!!!!!!!!!!!!!!!!!!!!!!onClick!!!!!!!!!!!!!!!!!!!!!");
        Log.d(msg2, "id" +Fingerprint +"!!!!!!!!!!!!!!!!!!!!!!!!onClick!!!!!!!!!!!!!!!!!!!!!");
        Log.d(msg2, "id" +Email +"!!!!!!!!!!!!!!!!!!!!!!!!onClick!!!!!!!!!!!!!!!!!!!!!");
        Log.d(msg2, "id" +Addtime +"!!!!!!!!!!!!!!!!!!!!!!!!onClick!!!!!!!!!!!!!!!!!!!!!");
    }

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

                                    userid = id;
                                    CheckLogin = username;
                                    CheckPassword = password;
                                    FirstName  = firstname;
                                    LastName = lastname;
                                    Fingerprint = fingerprint;
                                    Email = email;
                                    Addtime = addtime;

                                    Log.d(msg, "id" +userid +"!!!!!!!!!!!!!!!!!!!!!!!!GETUSERCLICK!!!!!!!!!!!!!!!!!!!!!");
                                    Log.d(msg, "id" +CheckLogin +"!!!!!!!!!!!!!!!!!!!!!!!!GETUSERCLICK!!!!!!!!!!!!!!!!!!!!!");
                                    Log.d(msg, "id" +CheckPassword +"!!!!!!!!!!!!!!!!!!!!!!!!GETUSERCLICK!!!!!!!!!!!!!!!!!!!!!");
                                    Log.d(msg, "id" +FirstName +"!!!!!!!!!!!!!!!!!!!!!!!!GETUSERCLICK!!!!!!!!!!!!!!!!!!!!!");
                                    Log.d(msg, "id" +LastName +"!!!!!!!!!!!!!!!!!!!!!!!!GETUSERCLICK!!!!!!!!!!!!!!!!!!!!!");
                                    Log.d(msg, "id" +Fingerprint +"!!!!!!!!!!!!!!!!!!!!!!!!GETUSERCLICK!!!!!!!!!!!!!!!!!!!!!");
                                    Log.d(msg, "id" +Email +"!!!!!!!!!!!!!!!!!!!!!!!!GETUSERCLICK!!!!!!!!!!!!!!!!!!!!!");
                                    Log.d(msg, "id" +Addtime +"!!!!!!!!!!!!!!!!!!!!!!!!GETUSERCLICK!!!!!!!!!!!!!!!!!!!!!");
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

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN)
        {
            switch (keyCode)
            {
                case KeyEvent.KEYCODE_ENTER:
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    View passwordEdit = findViewById(R.id.password);
                    inputMethodManager.hideSoftInputFromWindow(passwordEdit.getWindowToken(), 0);
                    return true;
                default:
                    break;
            }
        }
        return false;
    }
}

