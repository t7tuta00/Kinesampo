package com.example.toiminnallisuusv1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener{


        private ImageButton button;
        private Object Intent;


        @SuppressLint("WrongViewCast")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_shop);
            button = findViewById(R.id.imageButton1);
            Intent intent = getIntent();
            findViewById(R.id.backButton).setOnClickListener(this);
        }


        public void moveToMain2Activity(View view) {
            android.content.Intent intent = new Intent(this, ShopItemActivity.class);
            startActivity(intent);
        }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.backButton){
            Intent intent = new Intent(ShopActivity.this, MainViewActivity.class);
            startActivity(intent);
        }
    }
}
