package com.example.toiminnallisuusv1;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.stream.Stream;

public class WorkoutData extends ArrayList {
    private int workoutID;
    private String workoutName;
    private int cal;
    private String device;
    private String workoutStyle;
    private String date;
    private String name;
    private String time;
    private int userid;

    public WorkoutData(int workoutID, String workoutName, int cal, String device, String workoutStyle, String date, String name, String time, int userid) {
        this.workoutID = workoutID;
        this.workoutName = workoutName;
        this.cal = cal;
        this.device = device;
        this.workoutStyle = workoutStyle;
        this.date = date;
        this.name = name;
        this.time = time;
        this.userid = userid;


    }

}
