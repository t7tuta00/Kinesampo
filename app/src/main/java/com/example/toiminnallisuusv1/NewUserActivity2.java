package com.example.toiminnallisuusv1;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.EditText;
        import android.widget.Spinner;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonArrayRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;

public class NewUserActivity2 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    int id;
    String TAG = "LOG: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
    String Username;
    String CaloryGoal;
    String SportGoal;
    String Firstname;
    String Lastname;
    String Password;
    String Fingerprint;
    String Email;
    String Addtime;
    String Age;
    String Gender;
    String Height;
    String Weight;
    EditText firstnameText;
    EditText lastnameText;
    EditText ageText;
    EditText heightText;
    EditText weightText;
    //Spinner gender;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user2);

        if (getIntent() != null)
        {
            id = getIntent().getIntExtra("id",0);
            Email = getIntent().getStringExtra("email");
            Username = getIntent().getStringExtra("username");
            Password = getIntent().getStringExtra("password");
            Firstname = getIntent().getStringExtra("firstname");
            Lastname = getIntent().getStringExtra("lastname");
        }

        findViewById(R.id.takaisinButton).setOnClickListener(this);
        findViewById(R.id.okButton).setOnClickListener(this);
        firstnameText = findViewById(R.id.firstNameEditText);
        lastnameText = findViewById(R.id.lastNameEditText);
        ageText = findViewById(R.id.ikaEditText);
        heightText = findViewById(R.id.pituusEditText);
        weightText = findViewById(R.id.painoEditText);
        mQueue = Volley.newRequestQueue(this);

        Log.d(TAG, "\n"+ "onClick: "+Firstname+"\n");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        String[] categories = new String[] {
                "Sukupuoli", "Mies", "Nainen", "-" };

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.takaisinButton) {
            Intent takaisinIntent = new Intent(NewUserActivity2.this, NewUserActivity.class);
            startActivity(takaisinIntent);
        }
        else if (v.getId() == R.id.okButton) {
            Intent okIntent = new Intent(NewUserActivity2.this, MainViewActivity.class);

            editUserInfo();
            startActivity(okIntent);
        }
    }

    public void editUserInfo() {
        String url = "";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.PUT, url, null,
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

        //mQueue.add(request);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        }
}
