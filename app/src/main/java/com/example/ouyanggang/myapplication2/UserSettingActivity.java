package com.example.ouyanggang.myapplication2;

import android.os.Bundle;
import android.preference.PreferenceActivity;

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
