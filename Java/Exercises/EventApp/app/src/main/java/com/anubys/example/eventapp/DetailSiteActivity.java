package com.anubys.example.eventapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.anubys.example.eventapp.Modell.Event;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Author Created by Anubys on the 07.06.2020 */


public class DetailSiteActivity extends AppCompatActivity {
    private static final String TAG = DetailSiteActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.tv_details)
    TextView textViewDetails;

    private Unbinder unbinder;


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "TAG - DetailSiteActivity - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_site);

        unbinder = ButterKnife.bind(this);

        showDetails();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "TAG - DetailSiteActivity - onDestroy()");
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private void showDetails() {
        Log.i(TAG, "TAG - DetailSiteActivity - showDetails()");
        String eventKey = getIntent().getStringExtra("eventKey");
        Toast.makeText(getApplicationContext(), "Key: " + eventKey, Toast.LENGTH_SHORT).show();
        if (eventKey != null) {
            // Datenbankverbindung aufbauen
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            // Den Pfad zu Events herstellen
            DatabaseReference databaseReference = firebaseDatabase.getReference("Events");
            // Die Änderungen des Wertes über Listener mitteilen und aktuallisieren
            databaseReference.child(eventKey).addValueEventListener(new AddValueEventListener());
        }
    }


    //* ************************************************ *
    //*                 C L A S S E S                    *
    //* ************************************************ *
    private class AddValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Event event = dataSnapshot.getValue(Event.class);
            Log.i("Daten: ", Objects.requireNonNull(event).getName());

            String display = "<b> Event: " + event.getName() +" <br>" +
                             "Ort: " + event.getPlace()+"<br>"+
                             "Datum: " + event.getDate() + "<br>"+
                             "Beschreibung: " + "</b> <br>" + event.getDescription();

            if (textViewDetails != null) textViewDetails.setText(Html.fromHtml(display), TextView.BufferType.SPANNABLE);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
