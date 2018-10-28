package com.dam.christian.proyecto_android;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class QueryActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String PREFS = "My preferences";
    public static final String key = "key";
    Button queryAllStudent,queryAllTeacher,queryStudentCFGS,queryStudentCourse,queryEverybody;
    EditText course, CFGS;
    TextView prueba;
    String co, cf;
    MyDBAdapter myDBAdapter;
    Intent intent;
    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        //Def Variables
        queryAllStudent = findViewById(R.id.allqueryStudents);
        queryAllTeacher = findViewById(R.id.allqueryTeachers);
        queryStudentCFGS = findViewById(R.id.studentqueryCGFS);
        queryStudentCourse = findViewById(R.id.studentqueryCourse);
        queryEverybody = findViewById(R.id.queryeveryBody);
        course = findViewById(R.id.txtCourse);
        CFGS = findViewById(R.id.txtCFGS);

        //Action Buttons
        queryAllStudent.setOnClickListener(QueryActivity.this);
        queryAllTeacher.setOnClickListener(QueryActivity.this);
        queryStudentCFGS.setOnClickListener(QueryActivity.this);
        queryStudentCourse.setOnClickListener(QueryActivity.this);
        queryStudentCFGS.setOnClickListener(QueryActivity.this);
        queryEverybody.setOnClickListener(QueryActivity.this);
    }

    @Override
    public void onClick (View v) {

        int selectedId = v.getId();
        switch(selectedId){
            case R.id.studentqueryCourse:
                intent = new Intent(QueryActivity.this,ReplyActivity.class);
                intent.putExtra("key","Stdtcourse");
                co = course.getText().toString();
                intent.putExtra("course",co);
                startActivity(intent);
                break;

            case R.id.studentqueryCGFS:
                intent = new Intent(QueryActivity.this,ReplyActivity.class);
                intent.putExtra("key","Stdtcfgs");
                cf = CFGS.getText().toString();
                intent.putExtra("cfgs",cf);
                startActivity(intent);
                break;

            case R.id.allqueryStudents:
                Intent intent = new Intent(QueryActivity.this,ReplyActivity.class);
                intent.putExtra("key","allStudents");
                startActivity(intent);
                break;

            case R.id.allqueryTeachers:
                intent = new Intent(QueryActivity.this,ReplyActivity.class);
                intent.putExtra("key","allTeachers");
                startActivity(intent);
                break;
            case R.id.queryeveryBody:
                intent = new Intent(QueryActivity.this,ReplyActivity.class);
                intent.putExtra("key","allEverybody");
                startActivity(intent);
                break;

        }
    }

}
