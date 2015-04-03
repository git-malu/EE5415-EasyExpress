package com.example.ouyanggang.myapplication2.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ouyanggang.myapplication2.Classes.MyDatabase;
import com.example.ouyanggang.myapplication2.Classes.MySQLiteHelper;
import com.example.ouyanggang.myapplication2.R;


public class User extends ActionBarActivity {
    private Button mLogin,mRegister;
    private EditText mUserPhone,mUserPass;
    private static final int sRequestCodeUserRegister = 0;
    Boolean isLoginSuccess,isRegisterSuccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //initialize
        //wire up.
        mLogin = (Button) findViewById(R.id.login_button);
        mRegister = (Button) findViewById(R.id.register_button);
        mUserPhone = (EditText) findViewById(R.id.user_phone);
        mUserPass = (EditText) findViewById(R.id.user_pass);
        //set clickListeners
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            ThreadLogin mThreadLogin = new ThreadLogin("1");
//            mThreadLogin.start();
//            while (mThreadLogin.mBuffer == null){
//                ;
//            }
//            Toast.makeText(User.this, mThreadLogin.mBuffer,Toast.LENGTH_SHORT).show();

                //query exactly the corresponding password.
                MySQLiteHelper helper = new MySQLiteHelper(User.this);
                SQLiteDatabase db = helper.getReadableDatabase();
                String[] columns = {MyDatabase.UserInfo.USER_PASS, MyDatabase.UserInfo._ID};
                String selection = MyDatabase.UserInfo._ID+" like ?";
                String[] selectionArgs = {mUserPhone.getText().toString()};
                Cursor cs = null;
                //if password match
                try{
                    //not all the columns are queried. Only 2 columns !!
                    cs = db.query(MyDatabase.UserInfo.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
                    cs.moveToFirst();
                    if(cs.getString(0).equals(mUserPass.getText().toString())) {
                        MyDatabase.mCurrentUserName = cs.getString(1);
                        MyDatabase.mCurrentUserPhone = mUserPhone.getText().toString();
                        MyDatabase.mCurrentUserPass = mUserPass.getText().toString();
                        Toast.makeText(User.this, "Login success.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }catch (RuntimeException ex){
                    Toast.makeText(User.this,"Login failed.",Toast.LENGTH_SHORT).show();
                }
                finally {
                    cs.close();//remember to close the cursor
                }
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User.this,UserRegister.class);
                startActivityForResult(intent, sRequestCodeUserRegister);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            mUserPhone.setText(data.getStringExtra("user_phone"));
            mUserPass.setText(data.getStringExtra("user_pass"));
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
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
