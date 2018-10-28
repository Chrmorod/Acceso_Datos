package com.dam.christian.proyecto_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeacherActivity extends AppCompatActivity {

    EditText editName, editAge, editCFGS, editCourse, editOffice;
    Button btnSave, btnLoad;
    String na,cf,co,of,ag;
    MyDBAdapter myDBAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        //Define Variables
        editName = (EditText) findViewById(R.id.teachername);
        editAge = (EditText) findViewById(R.id.teacherage);
        editCFGS = (EditText) findViewById(R.id.teacherCFGS);
        editCourse = (EditText) findViewById(R.id.teachercourse);
        editOffice = (EditText) findViewById(R.id.teacheroffice);
        btnSave = (Button) findViewById(R.id.saveteacher);

        //btn Save in data base
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                //Save Input Data
                na = editName.getText().toString();
                ag = editAge.getText().toString();
                cf = editCFGS.getText().toString();
                co = editCourse.getText().toString();
                of = editOffice.getText().toString();

                Toast.makeText(getApplicationContext(),"Save data on SQLite: Name= "+na+" Age= "+ag+" CFGS= "+cf+" Course= "+co+" Office= "+of,Toast.LENGTH_LONG).show();

                myDBAdapter = new MyDBAdapter(TeacherActivity.this);
                myDBAdapter.open();
                myDBAdapter.insertTeacher(na,Integer.parseInt(ag),cf,co,of);
            }
        });
    }
}
