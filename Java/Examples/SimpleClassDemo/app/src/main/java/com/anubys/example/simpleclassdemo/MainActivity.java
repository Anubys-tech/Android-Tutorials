package com.anubys.example.simpleclassdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 27.03.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.et_input_animal)
    EditText editTextInputAnimal;
    @Nullable
    @BindView(R.id.btn_input_animal)
    Button buttonInputAnimal;
    @Nullable
    @BindView(R.id.tv_show_all_animals)
    TextView textViewShowAllAnimals;

    private Unbinder unbinder;
    private ArrayList<String> arrayListAnimals = new ArrayList<>();


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "TAG - MainActivity - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

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
    private void setListener() {
        Log.d(TAG, "TAG - MainActivity - setListener()");

        assert buttonInputAnimal != null;
        buttonInputAnimal.setOnClickListener(view -> inputAnimal());
    }

    private void inputAnimal() {
        Log.d(TAG, "TAG - MainActivity - InputAnimal()");

        Animal animal = new Animal();

        if (editTextInputAnimal != null) {
            animal.setsName(editTextInputAnimal.getText().toString());
            arrayListAnimals.add(animal.getsName());
            editTextInputAnimal.setText("");
        }

        if (textViewShowAllAnimals != null) {
            textViewShowAllAnimals.setText("");
        }

        for (int i = 0; i < arrayListAnimals.size(); i++) {
            if (textViewShowAllAnimals != null) {
                textViewShowAllAnimals.append(arrayListAnimals.get(i) + "\n");
            }

            Log.i(TAG, "Array: " + arrayListAnimals.get(i));
        }
    }
}
