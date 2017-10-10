package com.example.abhinav.myapplication;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;


public class Regist extends Fragment {


    private static final String TAG = Regist.class.getName();
    Button date,save;
    EditText name,amount,vendor;
    String timeStamp,typ,curr;
    MyDatabase myDatabase;
    public Regist() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regist, container, false);
        myDatabase = new MyDatabase(getActivity());
        myDatabase.opendb();
        final Spinner currency,type;

        currency = view.findViewById(R.id.currency);
        type = view.findViewById(R.id.type);
        date = view.findViewById(R.id.date);
        save = view.findViewById(R.id.save);
        name = view.findViewById(R.id.name);
        amount = view.findViewById(R.id.amount);
        vendor = view.findViewById(R.id.vendor);
        ArrayList<String> currency1 = new ArrayList<>();
        final ArrayList<String> type1 = new ArrayList<>();
        currency1.add("INR");
        currency1.add("USD");
        currency1.add("EURO");
        currency1.add("GBP");
        type1.add("Personal");
        type1.add("Family");
        type1.add("Business");
        type1.add("Others");
        ArrayAdapter<String> currencyArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, currency1);
        ArrayAdapter<String> typeArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, type1);
        currency.setAdapter(currencyArrayAdapter);
        type.setAdapter(typeArrayAdapter);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        Log.i(TAG, "timestamp : " + c);
                        timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(c.getTime());
                        Log.i(TAG, " answer ===" + timeStamp);
                    }

                }, mYear, mMonth, mDay);
                dpd.setCanceledOnTouchOutside(false);
                dpd.show();
            }
        });
        curr = currency.getSelectedItem().toString();
        Log.i(TAG, "currency : " + curr);
        typ = type.getSelectedItem().toString();
        Log.i(TAG, "type : " + typ);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabase.insertUser(UUID.randomUUID().toString(),name.getText().toString(),timeStamp,amount.getText().toString(),currency.getSelectedItem().toString(),type.getSelectedItem().toString(),vendor.getText().toString());
                Fragment show = new Show();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fr1, show, "abc");
                fragmentTransaction.addToBackStack("abc");
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myDatabase.close();
    }
}
