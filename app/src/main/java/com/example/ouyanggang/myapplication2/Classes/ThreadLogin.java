package com.example.ouyanggang.myapplication2.Classes;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.ouyanggang.myapplication2.Activities.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
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
    public String mBuffer = null;
    Activity mActivity;
    public ThreadLogin(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void run() {
        try {
            link = new Socket("144.214.103.195", PORT);
            in = new Scanner(link.getInputStream());
            out = new PrintWriter(link.getOutputStream(), true);
            out.println("login:Malu:2123");
            mBuffer = in.nextLine();


        } catch (UnknownHostException uhEx) {
            System.out.println("not host find");
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
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

        //UI thread
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mBuffer.equalsIgnoreCase("true")){
                    User.mLoginStatus = true;
                    Toast.makeText(mActivity, "Login Success.", Toast.LENGTH_SHORT).show();
                }else{
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
                            Toast.makeText(User.this, "OffLine Login success.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }catch (RuntimeException ex){
                        Toast.makeText(User.this,"OffLine Login failed.",Toast.LENGTH_SHORT).show();
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
