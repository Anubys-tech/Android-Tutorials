package com.anubys.example.sqlitedemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.anubys.example.sqlitedemo.DatabaseHelper.COLUMN_ID;

/** @Autor Created by Anubys on the 30.03.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.text)
    TextView textViewText;

    private Unbinder unbinder;


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "TAG - MainActivity - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
        DataSorce dataSorce = new DataSorce(this);

        dataSorce.open();

        ModelText modelText = new ModelText();
        modelText.setText("Test");
        dataSorce.writeDataModelText(modelText);

        ArrayList<ModelText> arrayListModelText = dataSorce.readModelText(COLUMN_ID + " > 2");
        Log.i(TAG, "Alte Liste: " + arrayListModelText.toString());

        //dataSorce.updateModelText(3, "Cool");
        Log.i(TAG, "Update Liste: " + arrayListModelText.toString());

        //dataSorce.deleteModelText(5);
        Log.i(TAG, "Delete Liste: " + arrayListModelText.toString());

        dataSorce.close();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "TAG - MainActivity - onDestroy()");
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *

}
