package com.anubys.example.eventapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.anubys.example.eventapp.Modell.Event;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Author Created by Anubys on the 07.04.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.lv_event_list)
    ListView listViewEvents;
    @Nullable
    @BindView(R.id.btn_add)
    Button buttonAdd;

    private Unbinder unbinder;
    private ArrayList<Event> arrayListEvent;
    private ListAdapter listAdapter;

    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "TAG - MainActivity - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        getList();
        setListener();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "TAG - MainActivity - onDestroy()");
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private void setListener() {
        Log.i(TAG, "TAG - MainActivity - setListener()");
        if (buttonAdd != null) buttonAdd.setOnClickListener(v -> goToActivity());
    }

    private void goToActivity() {
        Log.i(TAG, "TAG - MainActivity - goToActivity()");
        Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
        startActivity(intent);
    }

    private void getList() {
        Log.i(TAG, "TAG - MainActivity - getList()");

        arrayListEvent = new ArrayList<>();
        listAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, arrayListEvent);

        // Datenbankverbindung aufbauen
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        // Den Pfad zu Events herstellen
        DatabaseReference databaseReference = firebaseDatabase.getReference("Events");
        // Die Änderungen des Wertes über Listener mitteilen und aktuallisieren
        databaseReference.addChildEventListener(new AddChildEventListener());
    }


    //* ************************************************ *
    //*                 C L A S S E S                    *
    //* ************************************************ *
    private class AddChildEventListener implements ChildEventListener {

        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Event event = dataSnapshot.getValue(Event.class);
            if (event != null) event.setKey(dataSnapshot.getKey());
            arrayListEvent.add(event);
            if (listViewEvents != null) {
                listViewEvents.setAdapter(listAdapter);
                listViewEvents.setOnItemClickListener((parent, view, position, id) -> {
                    Event eventClick = arrayListEvent.get(position);
                    Intent intent = new Intent(getApplicationContext(), DetailSiteActivity.class);
                    intent.putExtra("eventKey", eventClick.getKey());
                    startActivity(intent);
                });
            }
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
