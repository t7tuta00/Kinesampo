package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ShopItemActivity extends AppCompatActivity {

    private ImageButton button;
    private Object Intent;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_item);

        button = findViewById(R.id.backButton);
    }


    public void moveToMainActivity(View view) {
        android.content.Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
    }
}
