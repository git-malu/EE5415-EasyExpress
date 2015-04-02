package com.example.ouyanggang.myapplication2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ouyanggang.myapplication2.Classes.MySQLiteHelper;
import com.example.ouyanggang.myapplication2.R;

public class UserRegister extends ActionBarActivity {
    private Button mRegister,mClear;
    private EditText mUserName,mUserPhone,mUserPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        //wire up!
        mRegister = (Button) findViewById(R.id.register_button);
        mClear = (Button) findViewById(R.id.clear_button);
        mUserName = (EditText) findViewById(R.id.user_name);
        mUserPass = (EditText) findViewById(R.id.user_pass);
        mUserPhone = (EditText) findViewById(R.id.user_phone);

        //set listeners
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MySQLiteHelper helper = new MySQLiteHelper(UserRegister.this);
                long result = helper.registerUserInfo(helper,mUserPhone.getText().toString(),mUserName.getText().toString(),mUserPass.getText().toString());
                if(result == -1){
                    Toast.makeText(UserRegister.this,"Registration failed, please try again later.",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UserRegister.this,"Registration success.",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("user_name",mUserName.getText().toString());
                    intent.putExtra("user_pass",mUserPass.getText().toString());
                    intent.putExtra("user_phone",mUserPhone.getText().toString());
                    setResult(RESULT_OK,intent);
                    finish();
                }


            }
        });

        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserPass.setText("");
                mUserName.setText("");
                mUserPhone.setText("");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_register, menu);
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
