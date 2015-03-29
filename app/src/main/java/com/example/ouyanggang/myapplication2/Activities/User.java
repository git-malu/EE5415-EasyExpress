package com.example.ouyanggang.myapplication2.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ouyanggang.myapplication2.R;


public class User extends ActionBarActivity {
    private Button mLogin,mRegister;
    Boolean isLoginSuccess,isRegisterSuccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //wire up.
        mLogin = (Button) findViewById(R.id.login_button);
        mRegister = (Button) findViewById(R.id.register_button);
        //set clickListeners
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLoginSuccess){
                    //Do sth like read all the userdata
                    Toast.makeText(User.this,getString(R.string.login_succeeded),Toast.LENGTH_SHORT);
                }else{
                    Toast.makeText(User.this,getString(R.string.login_failed),Toast.LENGTH_SHORT);
                }
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRegisterSuccess){
                    //
                    Toast.makeText(User.this,"Register Succeeded",Toast.LENGTH_SHORT);
                }else{
                    Toast.makeText(User.this,"Register Failed",Toast.LENGTH_SHORT);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user, menu);
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
        return super.onOptionsItemSelected(item);
    }
}
