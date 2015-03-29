package com.example.ouyanggang.myapplication2.Fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.ouyanggang.myapplication2.R;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    public MyDatePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        EditText edit_date = (EditText)getActivity().findViewById(R.id.edit_date);
        edit_date.setText(String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
    }



}
