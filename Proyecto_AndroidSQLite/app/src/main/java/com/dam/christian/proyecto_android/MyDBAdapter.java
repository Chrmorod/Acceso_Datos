package com.dam.christian.proyecto_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

// Aux Class for comprobation, update, creation, close, defined constant

public class MyDBAdapter {

    // Constant and Definition
    private static final String DATABASE_NAME = "dbflorida.db";
    private static final String DATABASE_TABLE_STUDENT = "students";
    private static final String DATABASE_TABLE_TEACHER = "teachers";
    private static final int DATABASE_VERSION = 1;

    // Field Table
    private static final String FIELD_NAME = "name";
    private static final String FIELD_AGE = "age";
    private static final String FIELD_CFGS = "cfgs";
    private static final String FIELD_COURSE = "course";
    private static final String FIELD_OFFICE = "office";
    private static final String FIELD_AVERAGE_MARK = "average_mark";

    // Create/Drop Table Student
    private static final String DATABASE_CREATE_STUDENT = "CREATE TABLE "+DATABASE_TABLE_STUDENT+" (_id integer primary key autoincrement, name text, age integer, cfgs text, course text, average_mark double);";
    private static final String DATABASE_DROP_STUDENT = "DROP TABLE IF EXISTS "+DATABASE_TABLE_STUDENT+";";

    // Create/Drop Table Teacher
    private static final String DATABASE_CREATE_TEACHER = "CREATE TABLE "+DATABASE_TABLE_TEACHER+" (_id integer primary key autoincrement, name text, age integer, cfgs text, course text, office text);";
    private static final String DATABASE_DROP_TEACHER = "DROP TABLE IF EXISTS "+DATABASE_TABLE_TEACHER+";";

    // App Context used by data base
    private final Context context;
    // Class SQLiteOpenHelper for  create/update data base
    private MyDbHelper dbHelper;
    // Instance of data base
    private SQLiteDatabase db;

    public MyDBAdapter (Context c){
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    // open data base
    public void open(){

       try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }

    // insert values table student
    public void insertStudent(String na, int ag, String cf, String co, double av){
        // Create new values of row to insert
        ContentValues newValues = new ContentValues();
        // Assign values for each field
        newValues.put(FIELD_NAME,na);
        newValues.put(FIELD_AGE,ag);
        newValues.put(FIELD_CFGS,cf);
        newValues.put(FIELD_COURSE,co);
        newValues.put(FIELD_AVERAGE_MARK,av);
        db.insert(DATABASE_TABLE_STUDENT,null,newValues);
    }

    // insert values table teacher
    public void insertTeacher(String na, int ag, String cf, String co, String of){
        // Create new values of row to insert
        ContentValues newValues = new ContentValues();
        // Assign values for each field
        newValues.put(FIELD_NAME,na);
        newValues.put(FIELD_AGE,ag);
        newValues.put(FIELD_CFGS,cf);
        newValues.put(FIELD_COURSE,co);
        newValues.put(FIELD_OFFICE,of);
        db.insert(DATABASE_TABLE_TEACHER,null,newValues);
    }

    // recover all Student
    public ArrayList<String> getAllStudent() {
        //Create ArrayList for students
        ArrayList<String> students = new ArrayList<String>();
        // Recover on cursor the info
        Cursor cursor = db.query(DATABASE_TABLE_STUDENT,null,null,null,null,null,null,null);

        // Start the cursor
        if(cursor != null && cursor.moveToFirst()){
            do{
                //recover string column 1 = name.
                students.add(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5));
            }while (cursor.moveToNext());

        }
        return students;
    }

    // recover all Teacher
    public ArrayList<String> getAllTeacher() {
        // Create ArrayList for teachers
        ArrayList<String> teachers = new ArrayList<String>();
        // Recover on cursor the info
        Cursor cursor = db.query(DATABASE_TABLE_TEACHER,null,null,null,null,null,null,null);

        // Start the cursor
        if(cursor != null && cursor.moveToFirst()){
            do{
                //recover string column 1 = name.
                teachers.add(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5));
            }while (cursor.moveToNext());

        }
        return teachers;
    }

    // recover student by CFGS
    public ArrayList<String> getStudentbyCFGS(String cfgs) {
        // Create Arraylist for students
        ArrayList<String> studentsbycfgs = new ArrayList<String>();
        // Recover on cursor the info
        String[] whereArgs = {cfgs};
        String whereClause = FIELD_CFGS + " = ?";

        Cursor cursor = db.query(DATABASE_TABLE_STUDENT,null,whereClause,whereArgs,null,null,null);

        // Start the cursor
        if(cursor != null && cursor.moveToFirst()){
            do{
                //recover string column 1 = name.
                studentsbycfgs.add(cursor.getString(1)+"   "+cursor.getString(3));

            }while (cursor.moveToNext());

        }
        return studentsbycfgs;
    }

    // recover student by Course
    public ArrayList<String> getStudentbyCourse(String course) {
        // Create Arraylist for students
        ArrayList<String> studentsbycourse = new ArrayList<String>();
        // Recover on cursor the info
        String[] whereArgs = {course}; //WHERE COURSE = 'course'; Example = 'DAM'
        String whereClause = FIELD_COURSE + " = ?"; //All rows

        Cursor cursor = db.query(DATABASE_TABLE_STUDENT,null,whereClause,whereArgs,null,null,null);

        // Start the cursor
        if(cursor != null && cursor.moveToFirst()){
            do{
                //recover string column 1 = name.
                    studentsbycourse.add(cursor.getString(1)+"  "+cursor.getString(4));

            }while (cursor.moveToNext());

        }
        return studentsbycourse;
    }

    // delete all values table student
    public void deleteStudent(){
        db.delete(DATABASE_TABLE_STUDENT,"1",null);
    }

    // delete all values table teacher
    public void deleteTeacher(){
       db.delete(DATABASE_TABLE_TEACHER,"1",null);
    }

    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }
        // Method Create
        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE_STUDENT);
            db.execSQL(DATABASE_CREATE_TEACHER);
        }

        // Method Update
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP_STUDENT);
            db.execSQL(DATABASE_DROP_TEACHER);
            onCreate(db);
        }

    }
}
