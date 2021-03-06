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
import android.widget.Toast;

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

        salasanaButton = findViewById(R.id.signInButton);
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
        Intent kirjauduIntent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(kirjauduIntent);
    }

    @Override
    public void onClick(View v)
    {
        if ( v.getId() ==R.id.signInButton)
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
                Toast toast = Toast.makeText(getApplicationContext(), "Väärä salasana", Toast.LENGTH_SHORT);
                toast.show();
                //Salasana ei täsmää,yritä uudelleen eri salasanalla
            }

        }
        else {
            //Kayttajaa ei ole olemassa, Luo uusi kayttaja?
        }

    }
}

