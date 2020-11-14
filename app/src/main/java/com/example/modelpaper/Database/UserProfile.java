package com.example.modelpaper.Database;

import android.provider.BaseColumns;

public class UserProfile {

    public UserProfile() {
    }

    public class Users implements BaseColumns{
        public static  final String TABLE_NAME="users";
        public static  final String COLUMN_NAME_USERNAME="username";
        public static  final String COLUMN_NAME_DOB="date_of_birth";
        public static  final String COLUMN_NAME_PASSWORD="password";
        public static  final String COLUMN_NAME_GENDER="gender";


    }
}
