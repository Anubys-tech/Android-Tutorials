package com.anubys.example.modellexampledemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 29.03.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.lv_contacts)
    ListView listViewContacts ;

    private Unbinder unbinder;
    private ArrayList<ModelTip> modelTipArrayList = null;


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "TAG - MainActivity - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        loadTravelTipDataSource();
        setListView();
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
    private void loadTravelTipDataSource() {
        Log.d(TAG, "TAG - MainActivity - loadTravelTipDataSource()");

        TravelTipDataSource travelTipDataSource = new TravelTipDataSource();
        modelTipArrayList = travelTipDataSource.getData();
    }

    private void setListView() {
        Log.d(TAG, "TAG - MainActivity - setListView()");

        if (listViewContacts != null) {
            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, modelTipArrayList);
            listViewContacts.setAdapter(adapter);
            listViewContacts.setOnItemClickListener(new setOnItemClickListenerContact());
        }
    }


    //* ************************************************ *
    //*                 C L A S S E S                    *
    //* ************************************************ *
    private class setOnItemClickListenerContact implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
            intent.putExtra("id", String.valueOf(position));
            startActivity(intent);
        }
    }
}
