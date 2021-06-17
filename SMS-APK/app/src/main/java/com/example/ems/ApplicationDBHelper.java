package com.example.ems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ApplicationDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String APPLICATION_TABLE_NAME = "Application";
    public static final String APPLICATION_COLUMN_ID = "id";
    public static final String APPLICATION_COLUMN_NAME = "Name";
    public static final String APPLICATION_COLUMNS_CGPA = "cgpa";
    public static final String APPLICATION_COLUMN_STATUS = "status";
    public static final String APPLICATION_COLUMN_DEGREE = "degree";
    public static final String APPLICATION_COLUMN_CNIC = "cnic";
    private HashMap hp;

    public ApplicationDBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table HALL " +
                        "(id integer primary key AUTOINCREMENT , Name text,cgpa text,capacity text,floors text, location text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS HALL");
        onCreate(db);
    }

    public boolean insertHall (String hallName, String capacity,String floors, String rent, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(APPLICATION_COLUMN_STATUS , capacity);
        contentValues.put(APPLICATION_COLUMN_DEGREE , floors);
        contentValues.put(APPLICATION_COLUMNS_CGPA , rent);
        contentValues.put(APPLICATION_COLUMN_NAME, hallName);
        contentValues.put(APPLICATION_COLUMN_CNIC, location);
        db.insert(APPLICATION_TABLE_NAME, null, contentValues);
        return true;
    }


    public Application getData(int Id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from HALL where id="+Id, null );
        res.moveToFirst();
        String id = res.getString((res.getColumnIndex("id")));
        String hallName = res.getString(res.getColumnIndex(APPLICATION_COLUMN_NAME));
        String capacity = res.getString(res.getColumnIndex(APPLICATION_COLUMN_STATUS));
        String floors = res.getString(res.getColumnIndex(APPLICATION_COLUMN_DEGREE));
        String rent = res.getString(res.getColumnIndex(APPLICATION_COLUMNS_CGPA));
        String location = res.getString(res.getColumnIndex(APPLICATION_COLUMN_CNIC));

        // craeting Event oject to store the info. reterieved from database
        Application application = new Application(hallName , location,capacity , floors , rent , id);
        return application;
    }

/*
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }
*/

    public Integer deleteHall (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("HALL",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public boolean updateHall (Integer id,String hallName, String capacity,String floors, String rent, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(APPLICATION_COLUMN_CNIC, location);
        contentValues.put(APPLICATION_COLUMN_NAME, hallName);
        contentValues.put(APPLICATION_COLUMNS_CGPA , rent);
        contentValues.put(APPLICATION_COLUMN_DEGREE , floors);
        contentValues.put(APPLICATION_COLUMN_STATUS , capacity);
        db.update("HALL", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public List<Application> getAllHalls() {
        //ArrayList<String> array_list = new ArrayList<String>();
        List<Application> applicationList = new ArrayList<>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from HALL", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            String id = res.getString((res.getColumnIndex("id")));
            String hallName = res.getString(res.getColumnIndex(APPLICATION_COLUMN_NAME));
            String capacity = res.getString(res.getColumnIndex(APPLICATION_COLUMN_STATUS));
            String floors = res.getString(res.getColumnIndex(APPLICATION_COLUMN_DEGREE));
            String rent = res.getString(res.getColumnIndex(APPLICATION_COLUMNS_CGPA));
            String location = res.getString(res.getColumnIndex(APPLICATION_COLUMN_CNIC));
            Log.i("HALL" , hallName );
            Application application = new Application(hallName , location , capacity, floors , rent , id );
            applicationList.add(application);
            //eventList.add(event);
            res.moveToNext();
        }
        return applicationList;
    }

    public ArrayList<String> getNames(){
        ArrayList<String> hallNames = new ArrayList<String>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select hallName from HALL", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){


            String hallName = res.getString(res.getColumnIndex(APPLICATION_COLUMN_NAME));
            hallNames.add(hallName);
            res.moveToNext();
        }

        return hallNames;

    }
}
