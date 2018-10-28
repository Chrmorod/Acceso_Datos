package com.dam.christian.proyecto_android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShowDataActivity extends AppCompatActivity{
    public static final String PREFS = "My preferences";
    public TextView tvName, tvUserName, tvBirthDate,tvGender;
    public static String name = "namekey";
    public static String username = "usernamekey";
    public static String birthdate = "birthkey";
    public static String gender ="genderkey";

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata);

        // Define Variables
        tvName = findViewById(R.id.txtName);
        tvUserName = findViewById(R.id.txtUserName);
        tvBirthDate = findViewById(R.id.txtFecha);
        tvGender = findViewById(R.id.txtGender);

        // Recover the Shared Preferences Object
        SharedPreferences mySharedPreferences = getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        // Recover the save values
        String nameu = mySharedPreferences.getString(name,"");
        String useru = mySharedPreferences.getString(username,"");
        String birtu = mySharedPreferences.getString(birthdate,"");
        String gendu = mySharedPreferences.getString(gender,"");

        //Show Data
        tvName.setText(nameu);
        tvUserName.setText(useru);
        tvBirthDate.setText(birtu);
        tvGender.setText(gendu);
    }
}
