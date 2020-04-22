package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import java.util.Calendar;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = "LOG: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
    int id;
    String id2;
    String Email;
    String Username;
    String Password;
    String Firstname;
    String Lastname;
    String Fingerprint;
    String Addtime;
    Date currentTime;
    EditText emailText;
    EditText usernameText;
    EditText passwordText;
    EditText firstNameText;
    EditText lastNameText;
    Button backButton;
    Button nextButton;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        emailText = findViewById(R.id.emailEditText);
        usernameText = findViewById(R.id.etunimiEditText);
        passwordText = findViewById(R.id.sukunimiEditText);
        firstNameText = findViewById(R.id.firstNameEditText);
        lastNameText = findViewById(R.id.lastNameEditText);
        backButton = findViewById(R.id.takaisinButton);
        backButton.setOnClickListener(this);
        nextButton = findViewById(R.id.okButton2);
        nextButton.setOnClickListener(this);
        mQueue = Volley.newRequestQueue(this);

        //currentTime = String.valueOf(Calendar.getInstance().getTime());
        id2 = Integer.toString(id);
        Log.d(TAG, id+"?????????????????????????Check?????????????????????????????????");
        //Log.d(TAG, id2+"?????????????????????????Check?????????????????????????????????");
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

    public void post_newUser() {
        //MainActivity mainActivity = new MainActivity();
        Email = emailText.getText().toString();
        Username = usernameText.getText().toString();
        Password = passwordText.getText().toString();
        Firstname = firstNameText.getText().toString();
        Lastname = lastNameText.getText().toString();
        String url = "http://ec2-35-172-199-159.compute-1.amazonaws.com/addNewUser?Kayttajanimi=" + Username + "&Etunimi=" + Firstname + "&Sukunimi=" + Lastname + "&Salasana=" + Password + "&Sormenjalki=" + Fingerprint + "&Sposti=" + Email + "&Lisaysaika=" + Addtime;


        Log.d(TAG, Email);
        Log.d(TAG, Username);
        Log.d(TAG, Password);
        //Log.d(TAG, id);

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

    public void onClick(View v) {
        if (v.getId() == R.id.takaisinButton) {
            Intent takaisinIntent = new Intent(NewUserActivity.this, MainActivity.class);
            startActivity(takaisinIntent);
        }
        else if (v.getId() == R.id.okButton) {
            Intent okIntent = new Intent(NewUserActivity.this, MainViewActivity.class);
            startActivity(okIntent);
        }
        else if (v.getId() == R.id.okButton2) {
            Intent nextIntent = new Intent(NewUserActivity.this, NewUserActivity2.class);
            nextIntent.putExtra("id", id);
            nextIntent.putExtra("email", Email);
            nextIntent.putExtra("username", Username);
            nextIntent.putExtra("password", Password);
            nextIntent.putExtra("firstname", Firstname);
            nextIntent.putExtra("lastname", Lastname);
            post_newUser();
            Handler myHandler = new Handler();
            myHandler.postDelayed(mMyRunnable, 2000);
            startActivity(nextIntent);
        }
    }

    public void takaisinIntent(View view) {
        Intent takaisinIntent = new Intent(NewUserActivity.this, MainActivity.class);
        startActivity(takaisinIntent);
    }

    private Runnable mMyRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            //Change state here
        }
    };

    //public void seuraavaIntent(View view) {
        //Intent takaisinIntent = new Intent(NewUserActivity.this, NewUserActivity2.class);
        //startActivity(takaisinIntent);
    //}
}
