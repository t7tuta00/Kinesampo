package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Recipe1Activity extends AppCompatActivity {

    ListView simpleList1;
    //tring listvie[] = {"Jauhoja - x dl", "Kananmunia - x kpl"};
    //tring listview2[] = {"tee näin","sitten näin", "sitten näin", "valmis"};

    ArrayList<String> listview1=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listview1);
        simpleList1 = (ListView)findViewById(R.id.listview1);
        simpleList1.setAdapter(arrayAdapter);
        listview1.add("Jauhoja x dl");
        listview1.add("kananmunia x kpl");
        listview1.add("maitoa x dl");
    }
}