package com.example.ems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Scholarship_One_Record extends AppCompatActivity {

    TextView title , org , date , desc , cgpa, province, deg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_one__record);

        Bundle bundel = getIntent().getExtras();
        String id = bundel.getString("id");

        ScholarshipDBHelper scholarshipDbHelper = new ScholarshipDBHelper(Scholarship_One_Record.this);
        Scholarship scholarship = scholarshipDbHelper.getData(Integer.parseInt(id));

        title = findViewById(R.id.view_title);
        title.setText(scholarship.getTitle());

        org = findViewById(R.id.view_org);
        org.setText(scholarship.getOrganizer());

        date = findViewById(R.id.view_date);
        date.setText(scholarship.getProvince());

        desc = findViewById(R.id.view_desc);
        desc.setText(scholarship.getDescription());

        cgpa = findViewById(R.id.view_limit);
        cgpa.setText(scholarship.getCgpa());

        province = findViewById(R.id.view_budget);
        province.setText(scholarship.getDegree());

        deg = findViewById(R.id.view_event);
        deg.setText(scholarship.getDate());


    }
}