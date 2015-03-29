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
public class MyThread extends Thread {
    private static final int PORT = 12345;
    private static Socket link = null;
    private static Scanner in;
    private static PrintWriter out;
    public String mBuffer = "first";
    public String txt1;
    String user = "Peter";

    public MyThread(String str) {
        txt1 = str;
    }

    @Override
    public void run() {
        try {
            //Toast.makeText(getApplicationContext(), "Connecting to server...", Toast.LENGTH_LONG).show();
            link = new Socket("144.214.102.18", PORT);
            //link = new Socket();
            //link.connect(new InetSocketAddress("172.20.10.4", 30000), PORT);
            //Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
            in = new Scanner(link.getInputStream());
            out = new PrintWriter(link.getOutputStream(), true);
            out.println("connect : peter");
            out.println("get : peter");
            //Loop
            String response;
            do{
                response = in.nextLine();
                String[] tokens = response.split("\\s*:\\s*");
                if(tokens[0].equals("msg")) {
                    String m = tokens[1];
                    for(int i = 2;i < tokens.length;i++) {
                        m = m + " : " + tokens[i];
                    }
                    String[] tokens2 = m.split("\\*");
                    String x="";
                    for(int i =0;i<tokens2.length;i++)
                        x=x + tokens2[i] +"\n";
                    mBuffer = x;
                    if(tokens[1].equalsIgnoreCase("Error"))
                        break;
                }
                //setContentView(R.layout.activity_reservation_list);
                //codes to process the data received from the server
            } while (true);

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
