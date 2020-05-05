package com.anubys.example.firebaseinsertobjectdemo;

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

/** @Autor Created by Anubys on the 06.04.2020 */

// TODO eine Liste erstellen wo alle objecte angezeigt werden. Beim Anklicken soll dann eine DetailActivty erscheinen
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.tv)
    TextView textViewMessage;

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

        // Datenbankverbindung aufbauen
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        // Den Pfad zu message/msg1 herstellen
        DatabaseReference databaseReference = firebaseDatabase.getReference("Events");

        addObjectEvent(databaseReference);
        getObject(databaseReference);
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
    //TODO die Funktion auslagern
    public void addObjectEvent(DatabaseReference databaseReference){
        Log.d(TAG, "TAG - MainActivity - addEvent()");
        ModellEvent event1 = new ModellEvent("Deeptrance", "Frankfurt", "Trance meets Techno","13.11.20",null);
        ModellEvent event2= new ModellEvent("Symphony and Metalica", "München", "Das Konzert des Jahres","12.4.20",null);
        ModellEvent event3 = new ModellEvent("Born to Rock", "Berlin", "Das Ultimative event für alle die Hardrock lieben","11.11.17",null);
        ModellEvent event4 = new ModellEvent("Coding is fun", "Frankfurt", "Die Convention für alle Coder.","02.04.20",null);
        ModellEvent event5 = new ModellEvent("Kulturschock", "Mainz", "Alternativ music party","03.05.20",null);
        databaseReference.push().setValue(event1);
        databaseReference.push().setValue(event2);
        databaseReference.push().setValue(event3);
        databaseReference.push().setValue(event4);
        databaseReference.push().setValue(event5);
    }

    private void getObject(DatabaseReference databaseReference) {
        Log.d(TAG, "TAG - MainActivity - getObject()");

        // Die Änderungen des Wertes über Listener mitteilen und aktuallisieren
        databaseReference.addValueEventListener(new AddValueEventListener());
    }


    //* ************************************************ *
    //*                 C L A S S E S                    *
    //* ************************************************ *
    private static class AddValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(DataSnapshot element : dataSnapshot.getChildren()){
                ModellEvent modellEvent = element.getValue(ModellEvent.class);

                if (modellEvent != null) {
                    Log.i("Daten: ",modellEvent.getName());
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
