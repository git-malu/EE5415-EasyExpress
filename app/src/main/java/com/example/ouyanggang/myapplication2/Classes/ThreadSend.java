package com.example.ouyanggang.myapplication2.Classes;

import android.app.Activity;
import android.widget.Toast;

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
public class ThreadSend extends Thread {
    private static final int PORT = 12345;
    private static Socket link = null;
    private static PrintWriter out;
    private static Scanner in;
    public String mBuffer = "null";
    public String mStringSend;
    Activity mActivity;



    public ThreadSend(Activity activity,String str) {
        mActivity = activity;
        mStringSend = str;
    }

    @Override
    public void run() {
        try {
            link = new Socket(MyDatabase.IP, PORT);
            in = new Scanner(link.getInputStream());
            out = new PrintWriter(link.getOutputStream(), true);
            out.println(mStringSend);
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
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mBuffer.equalsIgnoreCase("true")){
                    Toast.makeText(mActivity,"Send Order successfully.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
