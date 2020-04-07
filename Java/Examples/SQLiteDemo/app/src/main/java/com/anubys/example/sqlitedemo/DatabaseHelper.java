package com.anubys.example.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/** @Autor Created by Anubys on the 30.03.2020 */


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DBNAME = "testdb";
    private static final int DBVERSION = 1;
    public static final String TABLE_NAME = "text";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEXT = "text";

    private static final String TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUMN_TEXT + " TEXT NOT NULL )";

    public class DbSchema {
        public static final String DATABASE_NAME = "snoreDetector.db";
        public static final int DATABASE_VERSION = 1;

        public class RecordTable {
            public static final String RECORD_TABLE_NAME = "person";

            // Erstellung einer Tabelle mit Spalten
            public static final String CREATE_RECORD_TABLE = "CREATE TABLE " + RECORD_TABLE_NAME +
                    "(" +
                    Column.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    Column.PROFIL_ID + " INTEGER NOT NULL, " +
                    Column.RECORDINGDATE + " TEXT NOT NULL, " +
                    Column.STARTTIME + " INTEGER, " +
                    Column.ENDTIME + " INTEGER, " +
                    Column.COUNTER_BELL + " INTEGER, " +
                    Column.THRESHOLD + " TEXT, " +
                    Column.DURATION_BELL + " INTEGER, " +
                    Column.FORESTAGE + " TEXT, " +
                    Column.ORIENTATION + " TEXT, " +
                    Column.VALUESX + " TEXT, " +
                    Column.VALUESY + " TEXT " +
                    ")";

            public static final String DELETE_RECORD_TABLE = "DROP TABLE IF EXISTS " + RECORD_TABLE_NAME;

            // Eine Zeile mit diesen Spalten
            public class Column {
                public static final String ID = "id";
                public static final String PROFIL_ID = "profil_id";
                public static final String RECORDINGDATE = "recordingdate";
                public static final String STARTTIME = "starttime";
                public static final String ENDTIME = "endtime";
                public static final String COUNTER_BELL = "counter_bell";
                public static final String SERIES = "series";
                public static final String THRESHOLD = "threshold";
                public static final String DURATION_BELL = "duration_bell";
                public static final String FORESTAGE = "forestage";
                public static final String ORIENTATION = "orientation";
                public static final String VALUESX = "valuesx";
                public static final String VALUESY = "valuesy";
            } //End of Column
        } //End of RecordTable

        public class ProfilInfoTable {
            public static final String PROFIL_TABLE_NAME = "profil";

            // Erstellung einer Tabelle mit Spalten
            public static final String CREATE_PROFIL_TABLE = "CREATE TABLE " + PROFIL_TABLE_NAME +
                    "(" +
                    Column.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    Column.REGISTRATIONDATE + " TEXT NOT NULL, " +
                    Column.USER_NAME + " TEXT NOT NULL, " +
                    Column.EMAIL_NAME + " TEXT NOT NULL, " +
                    Column.LAST_NAME + " TEXT NOT NULL, " +
                    Column.FIRST_NAME + " TEXT NOT NULL, " +
                    Column.GENDER + " TEXT NOT NULL, " +
                    Column.BDAY + " TEXT NOT NULL, " +
                    Column.PASSWORD + " TEXT NOT NULL, " +
                    Column.IMAGE + " BLOB " +
                    ")";

            public static final String DELETE_PROFIL_TABLE = "DROP TABLE IF EXISTS " + PROFIL_TABLE_NAME;

            // Eine Zeile mit diesen Spalten
            public class Column {
                public static final String ID = "id";
                public static final String REGISTRATIONDATE = "registrationdate";
                public static final String LAST_NAME = "last_name";
                public static final String FIRST_NAME = "first_name";
                public static final String BDAY = "b_day";
                public static final String GENDER = "gender";
                public static final String USER_NAME = "user_name";
                public static final String EMAIL_NAME = "email";
                public static final String PASSWORD = "password";
                public static final String IMAGE = "image";
            } //End of Column
        } //End of RecordTable
    }

    //* ************************************************ *
    //*            K O N S T R U K T O R E N             *
    //* ************************************************ *
    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }


    //* ************************************************ *
    //*         O V E R R I D E - M E T H O D E N        *
    //* ************************************************ *
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "TAG - DatabaseHelper - onCreate()");
        db.execSQL(TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "TAG - DatabaseHelper - onUpgrade()");
    }
}
