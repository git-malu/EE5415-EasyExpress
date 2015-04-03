package com.example.ouyanggang.myapplication2.Fragments;


import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.example.ouyanggang.myapplication2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyListViewFragment extends ListFragment {


    public MyListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class MyCursorAdapter extends CursorAdapter{

        public MyCursorAdapter(Context context, Cursor c) {
            super(context, c, false);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return layoutInflater.inflate(R.layout.list_item,parent,false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

        }
    }
}
