package com.example.ems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class One_Application_Activity extends AppCompatActivity {

    TextView Name, CNIC, status, cgpa, degree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_hall_layout);


        Bundle bundel = getIntent().getExtras();
        String id = bundel.getString("id");

        ApplicationDBHelper applicationDBHelper = new ApplicationDBHelper(One_Application_Activity.this);
        Application application = applicationDBHelper.getData(Integer.parseInt(id));

        Name = findViewById(R.id.Hall_Name);
        Name.setText(application.getName());

        CNIC = findViewById(R.id.hall_capacity);
        CNIC.setText(application.getStatus());

        status = findViewById(R.id.hall_rent);
        status.setText(application.getDegree());

        cgpa = findViewById(R.id.hall_floors);
        cgpa.setText(application.getCGPA());

        degree = findViewById(R.id.hall_location);
        degree.setText(application.getCNIC());

    }

}