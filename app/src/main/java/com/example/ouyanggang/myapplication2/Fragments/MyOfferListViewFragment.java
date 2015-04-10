package com.example.ouyanggang.myapplication2.Fragments;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ouyanggang.myapplication2.Classes.MyDatabase;
import com.example.ouyanggang.myapplication2.Classes.MySQLiteHelper;
import com.example.ouyanggang.myapplication2.R;

/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class MyOfferListViewFragment extends ListFragment {
    Context mCtx;
    Cursor cs;

    public MyOfferListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCtx = getActivity();
        cs = queryDataBase();
        setListAdapter(new MyCursorAdapter(mCtx,cs));

        /*getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });*/
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
        TextView tv =  (TextView)v.findViewById(R.id.from_to_textView);
        Toast.makeText(getActivity(),tv.getText().toString(), Toast.LENGTH_SHORT).show();

    }


    //    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        getListView().set;
//
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextView tv =  (TextView)view.findViewById(R.id.from_to_textView);
//                Toast.makeText(getActivity(),"just test",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    public Cursor queryDataBase(){
        MySQLiteHelper helper = new MySQLiteHelper(mCtx);
        String[] columns = {
                MyDatabase.OrderRecords._ID,//0
                MyDatabase.OrderRecords.FROM,//1
                MyDatabase.OrderRecords.TO,//2
                MyDatabase.OrderRecords.COURIER_PHONE,//3
                MyDatabase.OrderRecords.EXPECTED_TIME,//4
                MyDatabase.OrderRecords.DESCRIPTIONS,//5
                MyDatabase.OrderRecords.STATUS//6
        };
        String whereClause = MyDatabase.OrderRecords.USER_PHONE+" like ?";
        String[] whereArgs = {MyDatabase.mCurrentUserPhone};
        Cursor cs = helper.queryOrderRecords(helper, columns, whereClause, whereArgs);
        return cs;
    }


    public class MyCursorAdapter extends CursorAdapter{

        public MyCursorAdapter(Context context, Cursor c) {
            super(context, c, false);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return layoutInflater.inflate(R.layout.list_item,parent,false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            //wire up!
            TextView from_to_textView = (TextView) view.findViewById(R.id.from_to_textView);
            TextView phone_textView = (TextView) view.findViewById(R.id.phone_textView);
            TextView time_textView = (TextView) view.findViewById(R.id.time_textView);
            TextView description_textView = (TextView) view.findViewById(R.id.description_textView);
            CheckBox status = (CheckBox) view.findViewById(R.id.status_checkBox);

            //fill in recycle view
            from_to_textView.setText("From " + cs.getString(1) + " to " + cs.getString(2));
            phone_textView.setText(cs.getString(3));
            time_textView.setText(cs.getString(4));
            description_textView.setText(cs.getString(5));
            if(cs.getString(6).equalsIgnoreCase("wait")){
                status.setChecked(false);
            }else if (cs.getString(6).equalsIgnoreCase("accepted")){
                status.setChecked(true);
            }
        }
    }

    /*class GetOfferTask implements Runnable {
        private static final int PORT = 12345;
        private  Socket link = null;
        private  Scanner in;
        private  PrintWriter out;
        public String mBuffer = "null";
        @Override
        public void run() {
            try {
                link = new Socket(MyDatabase.IP, PORT);
                in = new Scanner(link.getInputStream());
                out = new PrintWriter(link.getOutputStream(), true);
                out.println("inquiry_offer");
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
        }
    }*/
}
