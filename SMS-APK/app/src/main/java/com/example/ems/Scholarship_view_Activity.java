package com.example.ems;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import java.io.File;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class Scholarship_view_Activity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    Spinner degspin;
    Button addAndUpdateBtn;
    TextView title,from,description,province,gpa;
    String SelectedDegree, Date;
    String Deg;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_view_layout);
        initDatePicker();

        addAndUpdateBtn = findViewById(R.id.addAndUpdate);
        dateButton = findViewById(R.id.datePickerButton);
        title = findViewById(R.id.name_title);
        from = findViewById(R.id.from);
        description = findViewById(R.id.description);
        province = findViewById(R.id.Budget);
        gpa = findViewById(R.id.max_limit);

        dateButton.setText(getTodaysDate());


        ApplicationDBHelper applicationDBHelper = new ApplicationDBHelper(Scholarship_view_Activity.this);
        String Degree[] = {"Select Event","BS" , "BSC" , "MS" , "MSC" , "Mphil" , "PHD" , "Inermediate" };
        degspin = findViewById(R.id.evspinner);
        ArrayAdapter degadapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Degree);
        degspin.setAdapter(degadapter);

        degspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    onNothingSelected(parent);
                    addAndUpdateBtn.setEnabled(false);

                }
                else{
                    SelectedDegree = Degree[position];
                    addAndUpdateBtn.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                addAndUpdateBtn.setEnabled(false);
            }
        });

        if(getIntent().getExtras() != null){
            Log.i("EVENT" , "UPDATION STEP");

            Bundle bundel = getIntent().getExtras();
            id = bundel.getString("id");

            ScholarshipDBHelper scholarshipDbHelper = new ScholarshipDBHelper(Scholarship_view_Activity.this);
            Scholarship scholarship = scholarshipDbHelper.getData(Integer.parseInt(id));

            title.setText(scholarship.getTitle());
            from.setText(scholarship.getOrganizer());
            description.setText(scholarship.getDescription());

            province.setText(scholarship.getDegree());
            gpa.setText(scholarship.getCgpa());

        }

        addAndUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Title = title.getText().toString();
                String organizer = from.getText().toString();
                String limit = gpa.getText().toString();
                String budget = province.getText().toString();
                String Description = description.getText().toString();
                if(Title.isEmpty()){
                    ToastShow("Title");
                }
                else if(organizer.isEmpty()){
                    ToastShow("Organizer");
                }
                else if(limit.isEmpty()){
                    ToastShow("Limit ");
                }
                else if(budget.isEmpty()){
                    ToastShow("budget");
                }
                else if(Description.isEmpty()){
                    ToastShow("Description");
                }

                ScholarshipDBHelper scholarshipDbHelper = new ScholarshipDBHelper(Scholarship_view_Activity.this);

                if(getIntent().getExtras() != null ){

                    if(scholarshipDbHelper.updateEvent(Integer.parseInt(id)  , Title , organizer , SelectedDegree , budget , Date , Description ,limit ))
                    {
                        Log.i("EVENT" , "UPDATED");
                    }
                    else{
                        Log.i("EVENT" , "UPDATION FAILED");
                    }

                }
                else {
                    if (scholarshipDbHelper.insertEvent(Title, organizer, SelectedDegree, budget, Date, Description, limit)) {
                        Log.i("EVENT", "Event Added");
                    }
                    else{
                        Log.i("EVENT", "Event Addition Failed");
                    }
                }
            }
        });

    }

    private void writedata() {
        String json;
        InputStream is = null;
        try {
            is = getAssets().open("Event.json");

            File extdir1 = Environment.getExternalStorageDirectory();
            File myDatadir1 = new File(extdir1 , "/testing");
            if(!myDatadir1.exists()){
                Toast.makeText(this , "File dose not exist",Toast.LENGTH_LONG).show();
            }

            File myfile1 = new File(myDatadir1 , "Event.json");

            FileInputStream fis = new FileInputStream(myfile1);

            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            json = new String(buffer , "UTF-8");


            JSONArray jsonarray = new JSONArray(json);

            JSONObject obj = new JSONObject();

            obj.put("Title", title.getText().toString());
            obj.put("From", from.getText().toString());
            obj.put("Description", description.getText().toString());
            obj.put("Province", province.getText().toString());
            obj.put("Date", Date );
            //Log.i("MYTAG" , Date);
            obj.put("Degree", SelectedDegree);


            jsonarray.put(obj);


            File extdir = Environment.getExternalStorageDirectory();
            File myDatadir = new File(extdir , "/testing");
            if(!myDatadir.exists()){
                myDatadir.mkdir();
            }

            File myfile = new File(myDatadir , "Event.json");

            try {
                FileOutputStream fos = new FileOutputStream(myfile);
                fos.write(jsonarray.toString().getBytes());
                finish();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void LessGpa() {
        Toast.makeText(this , "GPA must be greater than 2.5",Toast.LENGTH_LONG).show();
    }


    // Coding for Date Picker
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                Date = date;
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    public void ToastShow(String field){
        Toast.makeText(this , field+" is required",Toast.LENGTH_LONG).show();

    }


}