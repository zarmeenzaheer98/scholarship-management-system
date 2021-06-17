package com.example.ems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class transection_view_activity extends AppCompatActivity {

    TextView Name , sureName , Age , CNIC ;
    Spinner TransectionSpinner;
    Button finishbtn;
    String id , event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participent_view_activity);

        Name = findViewById(R.id.parti_name);
        sureName = findViewById(R.id.parti_sure_name);
        Age  = findViewById(R.id.parti_age);
        CNIC = findViewById(R.id.cnic);
        EventSpinner = findViewById(R.id.parti_event_spinner);

        finishbtn = findViewById(R.id.AddUpdatePartiBtn);

        String events[] = {"Select..." , "Scholarship1","Scholarship2","Scholarship3","Scholarship4"};
        ArrayAdapter evetsadpater = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, events);
        EventSpinner.setAdapter(evetsadpater);

        EventSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    onNothingSelected(parent);
                    finishbtn.setEnabled(false);

                }
                else{
                    event = events[position];
                    finishbtn.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                finishbtn.setEnabled(false);
            }
        });



        finishbtn = findViewById(R.id.AddUpdatePartiBtn);

        if(getIntent().getExtras() != null){
            Log.i("EVENT" , "UPDATION STEP");

            Bundle bundel = getIntent().getExtras();
            id = bundel.getString("id");

            TransectionDBHelper transectionDBHelper = new TransectionDBHelper(transection_view_activity.this);
            Transection transection = transectionDBHelper.getData(Integer.parseInt(id));

            Name.setText(transection.getName());
            sureName.setText(transection.getSureName());
            Age.setText(transection.getAge());
            CNIC.setText(transection.getCNIC());

        }


        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = Name.getText().toString();
                String SureName = sureName.getText().toString();
                String age = Age.getText().toString();
                String cnic = CNIC.getText().toString();

                if(name.isEmpty()){
                    ToastShow("First Name");
                }
                else if(SureName.isEmpty()){
                    ToastShow("Sure Name");
                }
                else if(age.isEmpty()){
                    ToastShow(" Age ");
                }
                else if(cnic.isEmpty()){
                    ToastShow("CNIC");
                }


                TransectionDBHelper transectionDBHelper = new TransectionDBHelper(transection_view_activity.this);
                if(getIntent().getExtras() != null ){

                    if(transectionDBHelper.updateParticipent(Integer.parseInt(id)  , name , SureName , age  , event , cnic ))
                    {
                        Log.i("Participent" , "UPDATED");
                    }
                    else{
                        Log.i("Participent" , "UPDATION FAILED");
                    }

                }
                else {
                    if (transectionDBHelper.insertParticipent( name , SureName , age  , event , cnic )) {
                        Log.i("Participent", "Hall Added");
                    }
                    else{
                        Log.i("Participent", "Hall Addition Failed");
                    }

                }
            }
        });

    }

    public void ToastShow(String field){
        Toast.makeText(this , field+" is required",Toast.LENGTH_LONG).show();

    }
}