package com.example.ouyanggang.myapplication2.Activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.example.ouyanggang.myapplication2.R;

/**
 * Created by Lu on 3/25/2015.
 */
public class UserSettingActivity extends PreferenceActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
