package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        salasanaButton = (Button)findViewById(R.id.signInButton);
        textView = (TextView)findViewById(R.id.Login);

        final EditText salasanaEdit = (EditText) findViewById(R.id.password);
        salasanaEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                salasanaButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                salasanaButton.setVisibility(View.VISIBLE);
            }
        });
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
            case R.id.action_tili:
                startActivity(new Intent(getApplicationContext(), TiliAsetuksetActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void uusiKayttajaIntent(View view) {
        Intent uusiKayttajaIntent = new Intent(MainActivity.this, NewUserActivity.class);
        startActivity(uusiKayttajaIntent);
    }

    public void kirjauduIntent(View view) {
        Intent kirjauduIntent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(kirjauduIntent);
    }

    @Override
    public void onClick(View v)
    {
        if ( v.getId() ==R.id.Login)
        {
            EditText asd = (EditText)findViewById(R.id.editText);
            EditText asd2 = (EditText)findViewById(R.id.password);

            Login = asd.getText().toString();
            Password = asd2.getText().toString();

            Queue = Volley.newRequestQueue(this);
            GetUsers();
            CheckLogin();
        }
    }

    //Get-metodi, jolla tuodaan Kayttajatiedot tietokannasta Arraylistaan Userlist
    private void GetUsers()
    {
        String url = "ec2-3-91-81-187.compute-1.amazonaws.com/Kayttaja";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject o = jsonArray.getJSONObject(i);
                                Userlist.add(o);


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

    //tarkista sisäänkirjautuminen
    private void CheckLogin()
    {
        if (Userlist.contains(Login)) {
            //Kayttaja on olemassa, haetaan sen id.
            int index = Userlist.indexOf(Login);
            Object Userinformation = Userlist.get(index);
            String Userinfo = Userinformation.toString();

            //jsonparse pitää lisätä!!!
            if (Userinfo == Login+Password)
            {
                //kirjaudu sisään
                Intent kirjauduIntent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(kirjauduIntent);

            }else {
                //Salasana ei täsmää,yritä uudelleen eri salasanalla
            }

        }
        else {
            //Kayttajaa ei ole olemassa, Luo uusi kayttaja?
        }

    }
}
