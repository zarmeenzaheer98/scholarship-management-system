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

public class ScholarshipDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName2.db";
    public static final String SCHOLARSHIP_TABLE_NAME = "Scholarship";
    public static final String SCHOLARSHIP_COLUMN_ID = "id";
    public static final String SCHOLARSHIP_COLUMN_TITLE = "title";
    public static final String SCHOLARSHIP_COLUMN_ORGANIZER = "organizer";
    public static final String SCHOLARSHIP_COLUMN_DEGREE = "degree";
    public static final String SCHOLARSHIP_COLUMN_DATE = "Date";
    public static final String SCHOLARSHIP_COLUMN_PROVINCE = "province";
    public static final String SCHOLARSHIP_COLUMN_CGPA = "cgpa";
    public static final String SCHOLARSHIP_COLUMN_DESC = "Description";
    private HashMap hp;

    public ScholarshipDBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table Scholarship " +
                        "(id integer primary key AUTOINCREMENT , title text,organizer text,event text,budget text, Date text, Description text , max_limit text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS EVENTS");
        onCreate(db);
    }

    public boolean insertEvent (String title, String organizer,String event, String budget, String Date,String Description ,String max_limit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCHOLARSHIP_COLUMN_TITLE, title);
        contentValues.put(SCHOLARSHIP_COLUMN_ORGANIZER, organizer);
        contentValues.put(SCHOLARSHIP_COLUMN_PROVINCE, budget);
        contentValues.put(SCHOLARSHIP_COLUMN_DATE, Date);
        contentValues.put(SCHOLARSHIP_COLUMN_CGPA, max_limit);
        contentValues.put(SCHOLARSHIP_COLUMN_DESC, Description);
        contentValues.put(SCHOLARSHIP_COLUMN_DEGREE, event);
        db.insert(SCHOLARSHIP_TABLE_NAME, null, contentValues);
        return true;
    }


    public Scholarship getData(int Id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from EVENT where id="+Id, null );
        res.moveToFirst();
        String id = res.getString((res.getColumnIndex("id")));
        String title = res.getString(res.getColumnIndex(SCHOLARSHIP_COLUMN_TITLE));
        String organizer = res.getString(res.getColumnIndex(SCHOLARSHIP_COLUMN_ORGANIZER));
        String description = res.getString(res.getColumnIndex(SCHOLARSHIP_COLUMN_DESC));
        String budget = res.getString(res.getColumnIndex(SCHOLARSHIP_COLUMN_PROVINCE));
        String event_type = res.getString(res.getColumnIndex(SCHOLARSHIP_COLUMN_DEGREE));
        String date = res.getString(res.getColumnIndex(SCHOLARSHIP_COLUMN_DATE));
        String max_limit = res.getString(res.getColumnIndex(EVENT_COLUMN_LIMIT));
        // craeting Event oject to store the info. reterieved from database
        Scholarship scholarship = new Scholarship(title , organizer , description , max_limit , date , budget , event_type, id);
        return scholarship;
    }

/*
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }
*/

    public Integer deleteEvent (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("EVENT",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public boolean updateEvent (Integer id, String title, String organizer,String event, String budget, String Date,String Description ,String max_limit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCHOLARSHIP_COLUMN_TITLE, title);
        contentValues.put(SCHOLARSHIP_COLUMN_ORGANIZER, organizer);
        contentValues.put(SCHOLARSHIP_COLUMN_PROVINCE, budget);
        contentValues.put(SCHOLARSHIP_COLUMN_DATE, Date);
        contentValues.put(EVENT_COLUMN_LIMIT, max_limit);
        contentValues.put(SCHOLARSHIP_COLUMN_DESC, Description);
        contentValues.put(SCHOLARSHIP_COLUMN_DEGREE, event);
        db.update("EVENT", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public List<Scholarship> getAllEvents() {
        //ArrayList<String> array_list = new ArrayList<String>();
        List<Scholarship> scholarshipList = new ArrayList<>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from EVENT", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            String id = res.getString((res.getColumnIndex("id")));
            String title = res.getString(res.getColumnIndex(SCHOLARSHIP_COLUMN_TITLE));
            String organizer = res.getString(res.getColumnIndex(SCHOLARSHIP_COLUMN_ORGANIZER));
            String description = res.getString(res.getColumnIndex(SCHOLARSHIP_COLUMN_DESC));
            String budget = res.getString(res.getColumnIndex(SCHOLARSHIP_COLUMN_PROVINCE));
            String event_type = res.getString(res.getColumnIndex(SCHOLARSHIP_COLUMN_DEGREE));
            String date = res.getString(res.getColumnIndex(SCHOLARSHIP_COLUMN_DATE));
            String max_limit = res.getString(res.getColumnIndex(EVENT_COLUMN_LIMIT));
            Log.i("EVENT" , title );
            Scholarship scholarship = new Scholarship(title , organizer , description, max_limit , date , budget , event_type , id );
            scholarshipList.add(scholarship);
            res.moveToNext();
        }
        return scholarshipList;
    }

    public ArrayList<String> getNames(){
        ArrayList<String> Titles = new ArrayList<String>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select title from EVENT", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){


            String title = res.getString(res.getColumnIndex(SCHOLARSHIP_COLUMN_TITLE));
            Titles.add(title);
            res.moveToNext();
        }
        return Titles;

    }
}