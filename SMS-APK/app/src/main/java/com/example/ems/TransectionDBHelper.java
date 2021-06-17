package com.example.ems;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransectionDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName1.db";
    public static final String PARTICIPENT_TABLE_NAME = "PARTICIPENTS";
    public static final String PARTICIPENT_COLUMN_ID = "id";
    public static final String PARTICIPENT_COLUMN_Name = "name";
    public static final String PARTICIPENT_COLUMN_SURENAME = "sureName";
    public static final String PARTICIPENT_COLUMN_Age = "age";
    public static final String PARTICIPENT_COLUMN_EVENT = "event";
    public static final String PARTICIPENT_COLUMN_CNIC = "cnic";


    private HashMap hp;

    public TransectionDBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table PARTICIPENTS" +
                        "(id integer primary key AUTOINCREMENT , name text,sureName text,age text,event text,cnic text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS PARTICIPENT");
        onCreate(db);
    }

    public boolean insertParticipent (String name, String surename,String age,String event,String cnic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PARTICIPENT_COLUMN_Age, age);
        contentValues.put(PARTICIPENT_COLUMN_CNIC, cnic);
        contentValues.put(PARTICIPENT_COLUMN_EVENT, event);
        contentValues.put(PARTICIPENT_COLUMN_SURENAME, surename);
        contentValues.put(PARTICIPENT_COLUMN_Name, name);

        db.insert(PARTICIPENT_TABLE_NAME, null, contentValues);
        return true;
    }


    public Transection getData(int Id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from PARTICIPENTS where id="+Id, null );
        res.moveToFirst();
        String id = res.getString((res.getColumnIndex("id")));
        String name = res.getString((res.getColumnIndex(PARTICIPENT_COLUMN_Name)));
        String sureName = res.getString((res.getColumnIndex(PARTICIPENT_COLUMN_SURENAME)));
        String age = res.getString((res.getColumnIndex(PARTICIPENT_COLUMN_Age)));
        String event = res.getString((res.getColumnIndex(PARTICIPENT_COLUMN_EVENT)));
        String cnic = res.getString((res.getColumnIndex(PARTICIPENT_COLUMN_CNIC)));

        // craeting Event oject to store the info. reterieved from database
        Transection transection = new Transection(id , name, sureName ,  event , age, cnic);
        return transection;
    }

/*
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }
*/

    public Integer deleteParticipent (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("PARTICIPENTS",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public boolean updateParticipent (Integer id, String name, String surename,String age,String event,String cnic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PARTICIPENT_COLUMN_Age, age);
        contentValues.put(PARTICIPENT_COLUMN_CNIC, cnic);
        contentValues.put(PARTICIPENT_COLUMN_EVENT, event);
        contentValues.put(PARTICIPENT_COLUMN_SURENAME, surename);
        contentValues.put(PARTICIPENT_COLUMN_Name, name);
        db.update("PARTICIPENTS", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public List<Transection> getAllParticipents() {
        //ArrayList<String> array_list = new ArrayList<String>();
        List<Transection> transectionList = new ArrayList<>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from PARTICIPENTS", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            String id = res.getString((res.getColumnIndex("id")));
            String name = res.getString((res.getColumnIndex(PARTICIPENT_COLUMN_Name)));
            String sureName = res.getString((res.getColumnIndex(PARTICIPENT_COLUMN_SURENAME)));
            String age = res.getString((res.getColumnIndex(PARTICIPENT_COLUMN_Age)));
            String event = res.getString((res.getColumnIndex(PARTICIPENT_COLUMN_EVENT)));
            String cnic = res.getString((res.getColumnIndex(PARTICIPENT_COLUMN_CNIC)));

            // craeting Event oject to store the info. reterieved from database
            Transection transection = new Transection(id , name, sureName ,  event , age, cnic);
            transectionList.add(transection);
            res.moveToNext();
        }
        return transectionList;
    }

    /*
    public ArrayList<String> getNames(){
        ArrayList<String> Titles = new ArrayList<String>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select title from EVENT", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){


            String title = res.getString(res.getColumnIndex(EVENT_COLUMN_TITLE));
            Titles.add(title);
            res.moveToNext();
        }
        return Titles;

    }
*/
}