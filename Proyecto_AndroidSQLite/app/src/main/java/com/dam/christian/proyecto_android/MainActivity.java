package com.dam.christian.proyecto_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button infoTeacher, infoStudent, infoPreference,clearStudent,clearTeacher,queries;
    MyDBAdapter myDBAdapter;
    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_main);

        //Def Variables
        infoTeacher = findViewById(R.id.buttonteach);
        infoStudent = findViewById(R.id.buttonstude);
        infoPreference = findViewById(R.id.buttonpref);
        clearStudent = findViewById(R.id.buttonclearstude);
        clearTeacher = findViewById(R.id.buttonclearteach);
        queries = findViewById(R.id.buttonqueries);

        //Action Buttons
        infoTeacher.setOnClickListener(MainActivity.this);
        infoStudent.setOnClickListener(MainActivity.this);
        infoPreference.setOnClickListener(MainActivity.this);
        clearStudent.setOnClickListener(MainActivity.this);
        clearTeacher.setOnClickListener(MainActivity.this);
        queries.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick (View v) {

       int selectedId = v.getId();
       Intent intent;
        switch(selectedId){
            case R.id.buttonpref:
                intent = new Intent(MainActivity.this, PreferenceActivity.class);
                startActivity(intent);
                break;

            case R.id.buttonteach:
                intent = new Intent(MainActivity.this,TeacherActivity.class);
                startActivity(intent);
                break;

            case R.id.buttonstude:
                intent = new Intent(MainActivity.this,StudentActivity.class);
                startActivity(intent);
                break;

            case R.id.buttonclearstude:
                myDBAdapter = new MyDBAdapter(MainActivity.this);
                myDBAdapter.open();
                myDBAdapter.deleteStudent();
                break;

            case R.id.buttonclearteach:
                myDBAdapter = new MyDBAdapter(MainActivity.this);
                myDBAdapter.open();
                myDBAdapter.deleteTeacher();
                break;

            case R.id.buttonqueries:
                intent = new Intent(MainActivity.this,QueryActivity.class);
                startActivity(intent);
                break;
        }

    }
}
