package com.example.ems;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Applicationl_List_Activity extends AppCompatActivity {

    List<Application> applicationList = new ArrayList<>();
    ListView ApplicationSpinner;
    Button addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hall_list_activity);


        ApplicationDBHelper applicationDBHelper = new ApplicationDBHelper(Applicationl_List_Activity.this);
        applicationList = applicationDBHelper.getAllHalls();

        ApplicationSpinner = findViewById(R.id.Halls);

//        Log.i("MYTAG" , eventList.get(0).getTitle());


        AplicationAdapter aplicationAdapter = new AplicationAdapter(applicationList);

        ApplicationSpinner.setAdapter(aplicationAdapter);

        addbtn = findViewById(R.id.addHallbtn);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newActivity = new Intent( Applicationl_List_Activity.this , Application_view_Activity.class);
                startActivity(newActivity);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        ApplicationDBHelper applicationDBHelper = new ApplicationDBHelper(Applicationl_List_Activity.this);
        Log.i("MY HALL" , "Resumed");
        applicationList = applicationDBHelper.getAllHalls();

        ApplicationSpinner = findViewById(R.id.Halls);

//        Log.i("MYTAG" , eventList.get(0).getTitle());


        AplicationAdapter aplicationAdapter = new AplicationAdapter(applicationList);
        Log.i("MY HALL" , String.valueOf(aplicationAdapter.getCount()));

        ApplicationSpinner.setAdapter(aplicationAdapter);

    }
}