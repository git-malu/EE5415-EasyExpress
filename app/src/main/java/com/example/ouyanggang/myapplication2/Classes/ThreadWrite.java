package com.example.ouyanggang.myapplication2.Classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Lu on 3/29/2015.
 * use the constructor as input.
 * use the mBuffer as output.
 */
public class ThreadWrite extends Thread {
    private static final int PORT = 12345;
    private static Socket link = null;
    private static PrintWriter out;
    public String mBuffer = "first";
    public String mStringWrite;
    String user = "Peter";

    public ThreadWrite(String str) {
        mStringWrite = str;
    }

    @Override
    public void run() {
        try {
            link = new Socket("144.214.102.18", PORT);
            out = new PrintWriter(link.getOutputStream(), true);
//            out.println("connect : peter");
//            out.println("get : peter");
            //Loop
            String response;
                out.print(mStringWrite);
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
