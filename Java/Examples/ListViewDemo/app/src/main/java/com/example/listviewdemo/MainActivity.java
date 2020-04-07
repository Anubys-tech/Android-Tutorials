package com.example.listviewdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 12.03.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.lv_view)
    ListView listView;

    private Unbinder unbinder;
    private ArrayList<String> friends;


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "TAG - MainActivity - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        init();
        setListener();
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
    private void init() {
        Log.d(TAG, "TAG - MainActivity - init()");
        friends = new ArrayList<>();
        friends.add("Boris");
        friends.add("Christine");
        friends.add("Jens");
        friends.add("Edward");
        friends.add("Martin");
        friends.add("Phillip");
        friends.add("Florian");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, friends);

        if (listView != null) {
            listView.setAdapter(arrayAdapter);
        }
    }

    private void setListener() {
        Log.d(TAG, "TAG - MainActivity - setListener()");

        if (listView != null) {
            listView.setOnItemClickListener(new onItemClickListener());
        }
    }


    //* ************************************************ *
    //*                  C L A S S E S                   *
    //* ************************************************ *
    private class onItemClickListener implements android.widget.AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.txt_hello) + " " + friends.get(position), Toast.LENGTH_LONG).show();
        }
    }
}
