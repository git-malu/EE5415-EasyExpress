package com.example.ouyanggang.myapplication2.Fragments;


import android.app.Dialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.ouyanggang.myapplication2.R;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyTimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    public MyTimePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                true);
    }
//    DateFormat.is24HourFormat(getActivity())

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        EditText edit_time = (EditText)getActivity().findViewById(R.id.edit_time);
        edit_time.setText(String.valueOf(hourOfDay)+":"+String.valueOf(minute));
    }


}
