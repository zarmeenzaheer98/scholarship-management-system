package com.example.ems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Application_view_Activity extends AppCompatActivity {

    TextView Name, CNIC, CGPA, status, degreee;
    Button finishbtn;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hall_view_activity);

        Name = findViewById(R.id.HallName);
        CNIC = findViewById(R.id.HallCapacity);
        CGPA = findViewById(R.id.floors);
        status = findViewById(R.id.Rent);
        degreee = findViewById(R.id.location);

        finishbtn = findViewById(R.id.hallAddAndUpdateBtn);

        if(getIntent().getExtras() != null){
            Log.i("EVENT" , "UPDATION STEP");

            Bundle bundel = getIntent().getExtras();
            id = bundel.getString("id");

            ApplicationDBHelper applicationDBHelper = new ApplicationDBHelper(Application_view_Activity.this);
            Application application = applicationDBHelper.getData(Integer.parseInt(id));

            CNIC.setText(application.getStatus());
            Name.setText(application.getName());
            CGPA.setText(application.getCGPA());

            status.setText(application.getDegree());
            degreee.setText(application.getCNIC());

        }


        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = Application_view_Activity.this.Name.getText().toString();
                String Capacity = CNIC.getText().toString();
                String Floor = CGPA.getText().toString();
                String Rent = status.getText().toString();
                String Location = degreee.getText().toString();
                if(Name.isEmpty()){
                    ToastShow("Hall Name");
                }
                else if(Capacity.isEmpty()){
                    ToastShow("Capacity");
                }
                else if(Floor.isEmpty()){
                    ToastShow(" Floors ");
                }
                else if(Rent.isEmpty()){
                    ToastShow("Rent");
                }
                else if(Location.isEmpty()){
                    ToastShow("Location");
                }

                ApplicationDBHelper applicationDBHelper = new ApplicationDBHelper(Application_view_Activity.this);
                if(getIntent().getExtras() != null ){

                    if(applicationDBHelper.updateApplication(Integer.parseInt(id)  , Name , Capacity , Floor , Rent , Location ))
                    {
                        Log.i("APPLICATION" , "UPDATED");
                    }
                    else{
                        Log.i("APPLICATION" , "UPDATION FAILED");
                    }

                }
                else {
                    if (applicationDBHelper.insertApplication(Name , Capacity , Floor , Rent , Location)) {
                        Log.i("APPLICATION", "Hall Added");
                    }
                    else{
                        Log.i("APPLICATION", "Hall Addition Failed");
                    }

            }

        }


        });


    }
    public void ToastShow(String field){
        Log.i("MYTAG" ,    "` `");
        Toast.makeText(this , field+" is required",Toast.LENGTH_LONG).show();
        Log.i("MYTAG" ,    "` `");
    }

}