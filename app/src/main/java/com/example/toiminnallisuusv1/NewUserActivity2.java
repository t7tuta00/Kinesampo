package com.example.toiminnallisuusv1;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Spinner;

public class NewUserActivity2 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user2);

        findViewById(R.id.takaisinButton).setOnClickListener(this);
        findViewById(R.id.okButton).setOnClickListener(this);

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
            startActivity(okIntent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        }
}
