package com.anubys.example.eventapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anubys.example.eventapp.Modell.Event;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Author Created by Anubys on the 07.06.2020 */


public class AddEventActivity extends AppCompatActivity {
    private static final String TAG = AddEventActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.et_name)
    EditText editTextName;
    @Nullable
    @BindView(R.id.et_place)
    EditText editTextPlace;
    @Nullable
    @BindView(R.id.et_date)
    EditText editTextDate;
    @Nullable
    @BindView(R.id.et_description)
    EditText editTextDescription;
    @Nullable
    @BindView(R.id.btn_send)
    Button buttonSend;

    private Unbinder unbinder;
    private DatabaseReference databaseReference;


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "TAG - MainActivity - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        unbinder = ButterKnife.bind(this);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Events");

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
        if (buttonSend != null) buttonSend.setOnClickListener(v -> sendEvent());
    }

    private void sendEvent() {
        Log.i(TAG, "TAG - MainActivity - sendEvent()");
        String sName = "";
        String sPlace = "";
        String sDate = "";
        String sDescription = "";
        String failMessage = "";

        if (editTextName != null) {
            sName = editTextName.getText().toString();
            if (sName.isEmpty()) {
                failMessage = failMessage + getString(R.string.txt_please_enter_a_name);
            }
        }

        if (editTextDate != null) {
            sDate = editTextDate.getText().toString();
            if (sDate.isEmpty()) {
                failMessage = failMessage + getString(R.string.txt_please_enter_a_date);
            }
        }

        if (editTextPlace != null) {
            sPlace = editTextPlace.getText().toString();
            if (sPlace.isEmpty()) {
                failMessage = failMessage + getString(R.string.txt_please_enter_a_place);
            }
        }

        if (editTextDescription != null) {
            sDescription = editTextDescription.getText().toString();
            if (sDescription.isEmpty()) {
                failMessage = failMessage + getString(R.string.txt_please_enter_a_description);
            }
        }

        if (failMessage.isEmpty()) {
            Event event = new Event();
            event.setName(sName);
            event.setPlace(sPlace);
            event.setDate(sDate);
            event.setDescription(sDescription);

            databaseReference.push().setValue(event);
        }
        else
        {
            Toast.makeText(getApplicationContext(), failMessage, Toast.LENGTH_LONG).show();
        }
    }
}
