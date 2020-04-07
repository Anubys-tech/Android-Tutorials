package com.anubys.example.eventapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 07.04.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.tv)
    TextView textViewMessage;


    private Unbinder unbinder;
    private FirebaseDatabase firebaseDatabase;


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        // Datenbankverbindung aufbauen
        firebaseDatabase = FirebaseDatabase.getInstance();
        // Den Pfad zu message/msg1 herstellen
        DatabaseReference databaseReference = firebaseDatabase.getReference("message/msg1");
        // Die Änderungen des Wertes über Listener mitteilen und aktuallisieren
        databaseReference.addValueEventListener(new AddValueEventListener());
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    public void sendingMessage() {
        Log.d(TAG, "TAG - MainActivity - sendingMessage()");

    }


    //* ************************************************ *
    //*                 C L A S S E S                    *
    //* ************************************************ *
    private static class AddValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
