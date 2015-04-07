package com.example.ouyanggang.myapplication2.Classes;

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
    public String txt1;
    String user = "Peter";

    public ThreadLogin(String str) {
        txt1 = str;
    }

    @Override
    public void run() {
        try {
            link = new Socket("144.214.103.195", PORT);
            in = new Scanner(link.getInputStream());
            out = new PrintWriter(link.getOutputStream(), true);
            out.println("login:Malu:2123");
            //Loop

                mBuffer = in.nextLine();
//                mBuffer = "true";
                //setContentView(R.layout.activity_reservation_list);
                //codes to process the data received from the server


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
    }
}
