package com.anubys.example.firebasedemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 29.03.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.tv_message)
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
        // Ein Objekt erstellen
        DatabaseReference databaseReference = firebaseDatabase.getReference("message");
        // Ein Kindelement von Eltern erstellen und einen Wert setzten
        databaseReference.child("msg1").setValue("Hallo2!");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "TAG - MainActivity - onDestroy()");
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
