package com.dam.christian.proyecto_android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PreferenceActivity extends AppCompatActivity {

    public static final String PREFS = "My preferences";
    public static final String gender ="genderkey";
    public static final String name = "namekey";
    public static final String username = "usernamekey";
    public static final String birthdate = "birthkey";

    EditText editName, editUserName, editBirthDate;
    RadioButton rdSelected;
    RadioGroup radioGroup;
    Button btnSave, btnLoad;
    String n,u,b,g;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        //Define Variables
        editName = (EditText) findViewById(R.id.name);
        editUserName = (EditText) findViewById(R.id.userName);
        editBirthDate = (EditText) findViewById(R.id.birthDate);
        btnSave = (Button) findViewById(R.id.buttonSave);
        btnLoad = (Button) findViewById(R.id.buttonLoad);
        radioGroup = (RadioGroup) findViewById(R.id.rdgroup);

        //Save Input Data
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                // Create or withdraw the SharedPreferences Object.
                SharedPreferences mySharedPreferences = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);

                // selected name
                n = editName.getText().toString();

                // selected User Name
                u = editUserName.getText().toString();

                // selected BirthDate
                b = editBirthDate.getText().toString();

                //selected Id
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                rdSelected = (RadioButton) findViewById(selectedId);

                // selected gender (g)
                g = rdSelected.getText().toString();

                // Obtain editor for change the Preferences.
                SharedPreferences.Editor editor = mySharedPreferences.edit();

                // Save the new values.
                editor.putString(name, n);
                editor.putString(username, u);
                editor.putString(birthdate, b);
                editor.putString(gender, g);

                // Save the changes.
                editor.commit();
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                //Transfer Data
                Intent intent = new Intent(PreferenceActivity.this, ShowDataActivity.class);

                /*Directly*/
                //intent.putExtra("namekey", n);
                //intent.putExtra("usernamekey", u);
                //intent.putExtra("birthkey", b);
                //intent.putExtra("genderkey", g);

                startActivity(intent);
            }
        });
    }
}
