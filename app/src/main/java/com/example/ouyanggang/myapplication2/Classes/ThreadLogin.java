package com.example.ouyanggang.myapplication2.Classes;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ouyanggang.myapplication2.Activities.User;
import com.example.ouyanggang.myapplication2.R;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Lu on 3/29/2015.
 * use the constructor as input.
 * use the mBuffer as output.
 */
public class ThreadLogin extends Thread {
    private static final int PORT = 12345;
    private static Socket link = null;
    private static Scanner in;
    private static PrintWriter out;
    public String mBuffer = "null";

    String mUserPhone,mUserPass;

    Activity mActivity;
    public ThreadLogin(Activity activity,String user_phone, String user_pass) {
        mActivity = activity;
        mUserPhone = user_phone;
        mUserPass = user_pass;
    }

    @Override
    public void run() {
        try {
            link = new Socket(MyDatabase.IP, PORT);
            in = new Scanner(link.getInputStream());
            out = new PrintWriter(link.getOutputStream(), true);
            out.println("login:"+mUserPhone+":"+mUserPass);
            mBuffer = in.nextLine();
        } catch (UnknownHostException uhEx) {
            System.out.println("not host find");
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } catch (NoSuchElementException noEx){
            ;
        } finally {
            try {
                if (link != null) {
                    System.out.println("Closing down connection...");
                    link.close();
                }
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
            }
        }

        //Login
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //wire up!
                EditText mUserPhone = (EditText) mActivity.findViewById(R.id.user_phone);
                EditText mUserPass = (EditText) mActivity.findViewById(R.id.user_pass);
                if(mBuffer.equalsIgnoreCase("true")){
                    User.mLoginStatus = true;
                    Toast.makeText(mActivity, "Login Success.", Toast.LENGTH_SHORT).show();
                    MyDatabase.mCurrentUserPhone = mUserPhone.getText().toString();
                    MyDatabase.mCurrentUserPass = mUserPass.getText().toString();
                    User.mLoginStatus = true;
                    mActivity.finish();
                    Log.d("Login Success","finish?");
                }else{
                    MySQLiteHelper helper = new MySQLiteHelper(mActivity);
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
                            Toast.makeText(mActivity, "OffLine Login success.", Toast.LENGTH_SHORT).show();
                            User.mLoginStatus = true;
                            mActivity.finish();
                        }else{
                            Toast.makeText(mActivity,"OffLine Login failed.",Toast.LENGTH_SHORT).show();
                            User.mLoginStatus = false;
                        }
                    }catch (RuntimeException ex){
                        Toast.makeText(mActivity,"OffLine Login failed.",Toast.LENGTH_SHORT).show();
                        User.mLoginStatus = false;
                    }
                    finally {
                        cs.close();//remember to close the cursor
                        db.close();
                    }
                }
            }
        });
    }
}
