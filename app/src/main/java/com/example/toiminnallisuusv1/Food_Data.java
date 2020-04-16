package com.example.toiminnallisuusv1;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Food_Data extends ArrayList {
    private int foodid;
    private String foodname;
    private int cal;
    private int fat;
    private int carb;
    private int protein;
    private int userid;

    public Food_Data(int ruokaid, String name, int calories, int fat, int carbs, int protein, int userid) {
        this.foodid = ruokaid;
        this.foodname = name;
        this.cal = calories;
        this.fat = fat;
        this.protein =protein;
        this.carb = carbs;
        this.userid = userid;


    }

    @NonNull
    @Override
    public Stream stream() {
        return null;
    }
}

