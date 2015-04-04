package com.example.ouyanggang.myapplication2.Fragments;


import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.ouyanggang.myapplication2.Classes.MyDatabase;
import com.example.ouyanggang.myapplication2.Classes.MySQLiteHelper;
import com.example.ouyanggang.myapplication2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyListViewFragment extends ListFragment {
    Context mCtx;
    Cursor cs;

    public MyListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCtx = getActivity();
        cs = queryDataBase();
        setListAdapter(new MyCursorAdapter(mCtx,cs));
    }

    public Cursor queryDataBase(){
        MySQLiteHelper helper = new MySQLiteHelper(mCtx);
        String[] columns = {
                MyDatabase.OrderRecords._ID,//0
                MyDatabase.OrderRecords.FROM,//1
                MyDatabase.OrderRecords.TO,//2
                MyDatabase.OrderRecords.COURIER_PHONE,//3
                MyDatabase.OrderRecords.EXPECTED_TIME,//4
                MyDatabase.OrderRecords.DESCRIPTIONS,//5
                MyDatabase.OrderRecords.STATUS//6
        };
        String whereClause = MyDatabase.OrderRecords.USER_PHONE+" like ?";
        String[] whereArgs = {MyDatabase.mCurrentUserPhone};
        Cursor cs = helper.queryOrderRecords(helper, columns, whereClause, whereArgs);
        return cs;
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
            //wire up!
            TextView from_to_textView = (TextView) view.findViewById(R.id.from_to_textView);
            TextView phone_textView = (TextView) view.findViewById(R.id.phone_textView);
            TextView time_textView = (TextView) view.findViewById(R.id.time_textView);
            TextView description_textView = (TextView) view.findViewById(R.id.description_textView);
            CheckBox status = (CheckBox) view.findViewById(R.id.status_checkBox);

            //fill in
            from_to_textView.setText("From " + cs.getString(1) + " to " + cs.getString(2));
            phone_textView.setText(cs.getString(3));
            time_textView.setText(cs.getString(4));
            description_textView.setText(cs.getString(5));
            if(cs.getString(6).equalsIgnoreCase("wait")){
                status.setChecked(false);
            }else if (cs.getString(6).equalsIgnoreCase("accepted")){
                status.setChecked(true);
            }
        }
    }
}
