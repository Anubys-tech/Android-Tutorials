package com.anubys.example.firebasechatdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 02.04.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.tv_message)
    TextView textViewMessage;
    @Nullable
    @BindView(R.id.et_input)
    EditText editTextInput;
    @Nullable
    @BindView(R.id.et_person_name)
    EditText editTextPersonName;
    @Nullable
    @BindView(R.id.btn_send)
    Button buttonSend;

    private Unbinder unbinder;
    private FirebaseDatabase firebaseDatabase;


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
        firebaseDatabase = FirebaseDatabase.getInstance();
        // Den Pfad zu message/msg1 herstellen
        DatabaseReference databaseReference = firebaseDatabase.getReference("message/msg1");
        // Die Änderungen des Wertes über Listener mitteilen und aktuallisieren
        databaseReference.addValueEventListener(new AddValueEventListener());

        if (buttonSend != null) {
            buttonSend.setOnClickListener(view -> sendingMessage());
        }
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
    public void sendingMessage() {
        Log.d(TAG, "TAG - MainActivity - sendingMessage()");
        if (textViewMessage != null) {
            String oldMessage = textViewMessage.getText().toString();

            if (editTextPersonName != null && editTextInput != null) {
                String appendMessage = oldMessage + editTextPersonName.getText().toString() + ": " + editTextInput.getText().toString() + "\n";
                DatabaseReference reference = firebaseDatabase.getReference("message");
                reference.child("msg1").setValue(appendMessage);
            }
        }
    }


    //* ************************************************ *
    //*                 C L A S S E S                    *
    //* ************************************************ *
    private class AddValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (textViewMessage != null) {
                textViewMessage.setText(dataSnapshot.getValue(String.class));
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
