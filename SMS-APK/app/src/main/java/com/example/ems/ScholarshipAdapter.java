package com.example.ems;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ScholarshipAdapter extends BaseAdapter {

    List<Scholarship> scholarshipList;

    public ScholarshipAdapter(List<Scholarship> scholarshipList) {
        this.scholarshipList = scholarshipList;
    }

    @Override
    public int getCount() {
        return scholarshipList.size();
    }


    @Override
    public Object getItem(int position) {
        return scholarshipList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //View v = convertView;

        Log.i("EVENT" , scholarshipList.get(position).getId());
        String id = scholarshipList.get(position).getId();
        Bundle  bundle = new Bundle();
        bundle.putString("id" , id);



        LayoutInflater layout = LayoutInflater.from(parent.getContext());
        View view = layout.inflate(R.layout.eventlist , parent , false);
        ((TextView) view.findViewById(R.id.txt1)).setText(scholarshipList.get(position).getTitle()+" by "+ scholarshipList.get(position).getOrganizer());

        Button Read , Update , Delete;

        // Binding View Button
        Read= view.findViewById(R.id.btnview);
        Read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, Scholarship_One_Record.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        Update = view.findViewById(R.id.uptbtn);
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, Scholarship_view_Activity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

        if(position >=0) {
            Delete = view.findViewById(R.id.delbtn);
            Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("EVENT", "DELETING");
                    Context context = v.getContext();
                    ScholarshipDBHelper scholarshipDbHelper = new ScholarshipDBHelper(context);
                    if (scholarshipDbHelper.deleteEvent(Integer.parseInt(id)) != 0) {
                        removeViewAt(position);
                    } else {
                        Log.i("EVENT", "DELETION FAILED");
                    }
                }
            });
        }

        return view;

    }

    public void removeViewAt(int position){
        scholarshipList.remove(position);
        notifyDataSetChanged();
    }

}
