package com.dam.christian.proyecto_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.DatabaseMetaData;

public class StudentActivity extends AppCompatActivity {

    EditText editName, editAge, editCFGS, editCourse, editAverageMark;
    Button btnSave;
    String na,cf,co,ag,av;
    MyDBAdapter myDBAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        //Define Variables
        editName = (EditText) findViewById(R.id.studentName);
        editAge = (EditText) findViewById(R.id.studentAge);
        editCFGS = (EditText) findViewById(R.id.studentCFGS);
        editCourse = (EditText) findViewById(R.id.studentCourse);
        editAverageMark = (EditText) findViewById(R.id.studentmark);
        btnSave = (Button) findViewById(R.id.savestudent);

        // btn Save in data base
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {


                // Save Input Data
                na = editName.getText().toString();
                ag = editAge.getText().toString();
                cf = editCFGS.getText().toString();
                co = editCourse.getText().toString();
                av = editAverageMark.getText().toString();

                Toast.makeText(getApplicationContext(),"Save data on SQLite: Name= "+na+" Age= "+ag+" CFGS= "+cf+" Course= "+co+" Average Mark= "+av,Toast.LENGTH_LONG).show();
                myDBAdapter = new MyDBAdapter(StudentActivity.this);
                myDBAdapter.open();
                myDBAdapter.insertStudent(na,Integer.parseInt(ag),cf,co,Double.parseDouble(av));

                //myDBAdapter.insertStudent("Christian",25,"DAM","2º",5.5);

            }
        });
    }
}
