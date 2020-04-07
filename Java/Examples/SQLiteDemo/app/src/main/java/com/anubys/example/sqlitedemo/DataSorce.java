package com.anubys.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import static com.anubys.example.sqlitedemo.DatabaseHelper.*;

/** @Autor Created by Anubys on the 30.03.2020 */


public class DataSorce {
    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;


    //* ************************************************ *
    //*            K O N S T R U K T O R E N             *
    //* ************************************************ *

    public DataSorce(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    public void open(){
        Log.d(TAG, "TAG - DataSorce - open()");
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public void close(){
        Log.d(TAG, "TAG - DataSorce - close()");
        databaseHelper.close();;
    }

    public void writeDataModelText(ModelText modelText) {
        Log.d(TAG, "TAG - DataSorce - writeDataModelText()");

        ContentValues contentValues = new ContentValues();
        contentValues.put("text", modelText.getText());
        long insert = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        Log.i(TAG, "Insert: " + insert);
    }

    public ArrayList<ModelText> readModelText(String operator) {
        Log.d(TAG, "TAG - DataSorce - readModelText()");
        ArrayList<ModelText> arrayListModelText = new ArrayList<>();
        String[] columns = {COLUMN_ID, COLUMN_TEXT};

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns, operator, null, null, null, null);
        int id = cursor.getColumnIndex(COLUMN_ID);
        int text = cursor.getColumnIndex(COLUMN_TEXT);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            ModelText modelText = new ModelText();
            modelText.setId(cursor.getInt(id));
            modelText.setText(cursor.getString(text));
            Log.i(TAG, "Return: " + modelText.toString());
            arrayListModelText.add(modelText);
            cursor.moveToNext();
        }

        cursor.close();

        return (arrayListModelText);
    }

    public void updateModelText(int id, String newText) {
        Log.d(TAG, "TAG - DataSorce - updateModelText()");

        String filter = COLUMN_ID + "=" + id;
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TEXT, newText);
        sqLiteDatabase.update(TABLE_NAME, contentValues, filter, null);
    }

    public void deleteModelText(int id) {
        Log.d(TAG, "TAG - DataSorce - deleteModelText()");

        String filter = COLUMN_ID + "=" + id;
        sqLiteDatabase.delete(TABLE_NAME, filter, null);
    }
}
