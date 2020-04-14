package com.example.toiminnallisuusv1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class httpKayttajaPost extends Activity implements View.OnClickListener {

    TextView content;
    EditText email, login, pass;
    String Name1,Name2, Email, Login, Pass, Finger;
    EditText FirstName,SecondName,Age,Lenght,Weight;
    Button saveme;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_user);
        setContentView(R.layout.activity_new_user2);
        //content    =   (TextView)findViewById( R.id.content );

        email   =   (EditText)findViewById(R.id.emailEditText);
        login   =   (EditText)findViewById(R.id.etunimiEditText);
        pass    =   (EditText)findViewById(R.id.sukunimiEditText);
        Finger  =   "FingerprintFromSensor";
        FirstName   =   (EditText)findViewById(R.id.etunimiEditText);
        SecondName  =   (EditText)findViewById(R.id.sukunimiEditText);
        Age =   (EditText)findViewById(R.id.ikaEditText);
        Lenght  =   (EditText)findViewById(R.id.pituusEditText);
        Weight  =   (EditText)findViewById(R.id.painoEditText);

        saveme= (Button)findViewById(R.id.okButton2);
    }




    // Create GetText Metod
    public  void  GetText()  throws UnsupportedEncodingException
    {
        // Get user defined values
        Login   = login.getText().toString();
        Name1 = FirstName.getText().toString();
        Name2 = SecondName.getText().toString();
        Pass   = pass.getText().toString();
        Finger = "asd123";
        Email   = email.getText().toString();


        // Create data variable for sent values to server
        //INSERT INTO Kayttaja(Kayttajanimi,Etunimi,Sukunimi,Salasana,Sormenjalki,Sposti,Lisaysaika)
        //VALUES('Kilpikalevi','Taneli','Turpeinen','salasana','Sormenjalki','tt@gmail.com',TIME);

        String data = URLEncoder.encode("Kayttajanimi", "UTF-8")
                + "=" + URLEncoder.encode(Login, "UTF-8");

        data += "&" + URLEncoder.encode("Etunimi", "UTF-8") + "="
                + URLEncoder.encode(Name1, "UTF-8");

        data += "&" + URLEncoder.encode("Sukunimi", "UTF-8")
                + "=" + URLEncoder.encode(Name2, "UTF-8");

        data += "&" + URLEncoder.encode("Salasana", "UTF-8")
                + "=" + URLEncoder.encode(Pass, "UTF-8");

        data += "&" + URLEncoder.encode("Sormenjalki", "UTF-8")
                + "=" + URLEncoder.encode(Finger, "UTF-8");

        data += "&" + URLEncoder.encode("Sposti", "UTF-8")
                + "=" + URLEncoder.encode(Email, "UTF-8");

        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {

            // Defined URL  where to send data
            URL url = new URL("ec2-3-91-81-187.compute-1.amazonaws.com/addNewUser");
            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }

            text = sb.toString();
        }
        catch(Exception ex)
        {

        }
        finally
        {
            try
            {

                reader.close();
            }

            catch(Exception ex) {}
        }

        // Show response on activity
        //content.setText( text  );
    }

    @Override
    public void onClick(View v)
    {
        if ( v.getId() ==R.id.okButton2)
        {
            try{
                GetText();
            }
            catch(Exception ex)
            {
                content.setText(" url exeption! " );
            }
        }

    }
}

