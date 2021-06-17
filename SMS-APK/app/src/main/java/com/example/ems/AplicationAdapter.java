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

public class AplicationAdapter extends BaseAdapter {
    List<Application> applicationList;

    public AplicationAdapter(List<Application> applicationList) {
        Log.i("MY HALL" , "ADAPTER ADDED");
        this.applicationList = applicationList;
    }

    @Override
    public int getCount() {
        return applicationList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String id = applicationList.get(position).getId();
        Bundle bundle = new Bundle();
        bundle.putString("id" , id);


        LayoutInflater layout = LayoutInflater.from(parent.getContext());
        View view = layout.inflate(R.layout.hall_list , parent , false);
        ((TextView) view.findViewById(R.id.hall_list_view)).setText(applicationList.get(position).getName()+" : "+ applicationList.get(position).getCNIC());

        Button Read , Update , Delete;

        // Binding View Button
        Read= view.findViewById(R.id.hallViewBtn);
        Read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, One_Application_Activity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        Update = view.findViewById(R.id.hallUpdateBtn);
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, Application_view_Activity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

        if(position >=0) {
            Delete = view.findViewById(R.id.hallDeleteBtn);
            Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("EVENT", "DELETING");
                    Context context = v.getContext();
                    ApplicationDBHelper applicationDBHelper = new ApplicationDBHelper(context);
                    if (applicationDBHelper.deleteHall(Integer.parseInt(id)) != 0) {
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
        applicationList.remove(position);
        notifyDataSetChanged();
    }

}
