package com.dam.christian.proyecto_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class ReplyActivity extends AppCompatActivity {
    MyDBAdapter myDBAdapter;
    TextView reply;
    String st,te,ev,ev2,studentcourse,studentcfgs;
    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        Intent intent = this.getIntent();
        String data = intent.getExtras().getString("key");
        // SHOW ALL STUDENTS
        if(data.equals("allStudents")) {

            // Open Adapter
            myDBAdapter = new MyDBAdapter(this);
            myDBAdapter.open();

            // Define TextView for reply
            reply = (TextView) this.findViewById(R.id.replytxt);

            // Save Information in ArrayList
            ArrayList<String> students = myDBAdapter.getAllStudent();
            // Get the Enumeration object
            Enumeration<String> estudent = Collections.enumeration(students);

            st = "";
            while (estudent.hasMoreElements()) {
                st =  estudent.nextElement() + "\n" + st ;
            }

            // Show Info
            reply.setText(st);
        }
        // SHOW ALL TEACHERS
        if(data.equals("allTeachers")){

            myDBAdapter = new MyDBAdapter(this);
            myDBAdapter.open();

            reply = (TextView) this.findViewById(R.id.replytxt);

            ArrayList<String> teachers = myDBAdapter.getAllTeacher();

            Enumeration<String> eteacher = Collections.enumeration(teachers);

            te = "";
            while (eteacher.hasMoreElements()) {
                te =  eteacher.nextElement()+ "\n" + te;
            }
            // show info
            reply.setText(te);
        }
        // SHOW ALL EVERYBODY
        if(data.equals("allEverybody")){

            myDBAdapter = new MyDBAdapter(this);
            myDBAdapter.open();

            reply = (TextView) this.findViewById(R.id.replytxt);
            // All Teachers:
            ArrayList<String> teachers = myDBAdapter.getAllTeacher();

            Enumeration<String> eeverybody = Collections.enumeration(teachers);
            ev = "";
            while (eeverybody.hasMoreElements()) {
                ev = eeverybody.nextElement()+ "\n" +ev;
            }
            // All Students
            ArrayList<String> students = myDBAdapter.getAllStudent();

            Enumeration<String> eeverybody2 = Collections.enumeration(students);
            ev2="";
            while (eeverybody2.hasMoreElements()){
                ev2 = eeverybody2.nextElement()+"\n"+ev2;
            }
            reply.setText("TEACHERS: \n-----------\n"+ev+"\nSTUDENTS: \n----------\n"+ev2);
        }

        // SHOW STUDENT BY COURSE
        if(data.equals("Stdtcourse")){

            myDBAdapter = new MyDBAdapter(this);
            myDBAdapter.open();

            reply = (TextView) this.findViewById(R.id.replytxt);
            // Recover the append text
            String course = intent.getExtras().getString("course");

            ArrayList<String> stubycou = myDBAdapter.getStudentbyCourse(course);

            Enumeration<String> estucou = Collections.enumeration(stubycou);

            studentcourse = "";
            while (estucou.hasMoreElements()) {
                studentcourse =  estucou.nextElement()+ "\n" + studentcourse;
            }

            // show info
            reply.setText(studentcourse);
        }

        // SHOW STUDENT BY CFGS
        if(data.equals("Stdtcfgs")){

            myDBAdapter = new MyDBAdapter(this);
            myDBAdapter.open();

            reply = (TextView) this.findViewById(R.id.replytxt);
            // Recover the append text
            String cfgs = intent.getExtras().getString("cfgs");

            ArrayList<String> stubycfgs = myDBAdapter.getStudentbyCFGS(cfgs);

            Enumeration<String> estucfgs = Collections.enumeration(stubycfgs);

            studentcfgs = "";
            while (estucfgs.hasMoreElements()) {
                studentcfgs =  estucfgs.nextElement()+ "\n" + studentcfgs;
            }

            // show info
            reply.setText(studentcfgs);
        }

    }
}
