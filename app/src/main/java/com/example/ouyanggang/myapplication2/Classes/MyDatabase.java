package com.example.ouyanggang.myapplication2.Classes;

import android.provider.BaseColumns;

/**
 * Created by malu on 4/2/15.
 */
public class MyDatabase {
    public static final String DATABASE_NAME = "easy_express_database";

    public static abstract class UserInfo implements BaseColumns{
        //phone number is used as _ID of baseCoulums
        public static final String TABLE_NAME = "user_info";
        public static final String USER_NAME = "user_name";
        public static final String USER_PASS = "user_pass";
    }
}
