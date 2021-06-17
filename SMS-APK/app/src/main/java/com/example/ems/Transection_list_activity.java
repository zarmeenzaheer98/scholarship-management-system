package com.example.ems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Transection_list_activity extends AppCompatActivity {


    List<Transection> transectionList = new ArrayList<>();
    ListView partiSpinner;
    Button addbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participent_list_activity);


        TransectionDBHelper transectionDBHelper = new TransectionDBHelper(Transection_list_activity.this);
        transectionList = transectionDBHelper.getAllParticipents();

        partiSpinner = findViewById(R.id.Particpents);

//        Log.i("MYTAG" , eventList.get(0).getTitle());


        TransectionAdapter transectionAdapter = new TransectionAdapter(transectionList);

        partiSpinner.setAdapter(transectionAdapter);

        addbtn = findViewById(R.id.addPartbtn);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newActivity = new Intent( Transection_list_activity.this , transection_view_activity.class);
                startActivity(newActivity);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        TransectionDBHelper transectionDBHelper = new TransectionDBHelper(Transection_list_activity.this);
        Log.i("MY Participents" , "Resumed");
        transectionList = transectionDBHelper.getAllParticipents();

        partiSpinner = findViewById(R.id.Particpents);

//        Log.i("MYTAG" , eventList.get(0).getTitle());


        partiSpinner = findViewById(R.id.Particpents);

//        Log.i("MYTAG" , eventList.get(0).getTitle());


        TransectionAdapter transectionAdapter = new TransectionAdapter(transectionList);

        partiSpinner.setAdapter(transectionAdapter);

    }
}