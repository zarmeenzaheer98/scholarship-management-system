package com.example.ems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Transection_One_Record extends AppCompatActivity {
    TextView name , sureName , age , cnic , event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participent_one_record);


        Bundle bundel = getIntent().getExtras();
        String id = bundel.getString("id");

        TransectionDBHelper transectionDBHelper = new TransectionDBHelper(Transection_One_Record.this);
        Transection transection = transectionDBHelper.getData(Integer.parseInt(id));

        name = findViewById(R.id.First_Name);
        name.setText(transection.getName());

        sureName = findViewById(R.id.Sure_Name);
        sureName.setText(transection.getSureName());

        age = findViewById(R.id.AgE);
        age.setText(transection.getAge());

        cnic = findViewById(R.id.CniC);
        cnic.setText(transection.getCNIC());

        event = findViewById(R.id.EveNt);
        event.setText(transection.getEvent());




    }
}