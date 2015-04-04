package com.example.ouyanggang.myapplication2.Activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ouyanggang.myapplication2.Classes.MyDatabase;
import com.example.ouyanggang.myapplication2.Classes.MySQLiteHelper;
import com.example.ouyanggang.myapplication2.Fragments.MyDatePickerFragment;
import com.example.ouyanggang.myapplication2.Fragments.MyTimePickerFragment;
import com.example.ouyanggang.myapplication2.R;


public class SendStart extends ActionBarActivity {
    private Toolbar mToolbar;
    private Button mButtonConfrim,mButtonCancel;
    private EditText mFrom,mTo,mDes,mExTime,mExDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_start);
        //set up the toolbar.
        mToolbar = (Toolbar)findViewById(R.id.tool_bar);
        mToolbar.setBackgroundColor(getResources().getColor(R.color.primary_orange));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //wire up!
        mButtonConfrim = (Button) findViewById(R.id.confirm);
        mButtonCancel = (Button) findViewById(R.id.cancel);
        mFrom = (EditText) findViewById(R.id.edit_from);
        mTo = (EditText) findViewById(R.id.edit_to);
        mDes = (EditText) findViewById(R.id.edit_des);
        mExTime = (EditText) findViewById(R.id.edit_time);
        mExDate = (EditText) findViewById(R.id.edit_date);

        //set listeners
        mButtonConfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySQLiteHelper helper = new MySQLiteHelper(SendStart.this);
                helper.insertOrderRecord(helper,
                        mFrom.getText().toString(),
                        mTo.getText().toString(),
                        MyDatabase.mCurrentUserPhone,
                        null,//courier phone
                        mExTime.getText().toString()+" "+mExDate.getText().toString(),
                        mDes.getText().toString(),
                        "wait");//order status set to "wait"
            }
        });

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_table, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new MyTimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new MyDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
