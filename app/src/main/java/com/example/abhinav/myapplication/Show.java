package com.example.abhinav.myapplication;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;


public class Show extends Fragment {


    private static final String TAG = Show.class.getName();
    List<User> users = new ArrayList<>();
    MyDatabase myDatabase;
    public Show() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycle);
        RecyclerView.LayoutManager  layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(layoutManager);
        myDatabase = new MyDatabase(getActivity());
        myDatabase.opendb();
        Cursor cursor = myDatabase.getUserAll();
        while (cursor.moveToNext())
        {
            User use = new User();
            use.setId(cursor.getString(0));
            use.setName(cursor.getString(1));
            use.setAmount(cursor.getString(2));
            use.setCurrency(cursor.getString(3));
            use.setType(cursor.getString(4));
            use.setVendor(cursor.getString(5));
            use.setDate(cursor.getString(6));
            users.add(use);
        }
        try {
            Log.i(TAG,"list of elements in database"+new ObjectMapper().writeValueAsString(users));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(new ShowAdapter(users));

        return view;
    }

}
