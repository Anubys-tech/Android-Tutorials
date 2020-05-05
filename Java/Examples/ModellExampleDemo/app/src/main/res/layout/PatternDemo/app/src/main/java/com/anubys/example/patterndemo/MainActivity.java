package com.anubys.example.patterndemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 26.03.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.tv_result)
    TextView textViewResult;
    @Nullable
    @BindView(R.id.et_input_email)
    EditText editTextInputEmail;
    @Nullable
    @BindView(R.id.btn_heck_input)
    Button buttonCheckInput;

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

        assert buttonCheckInput != null;
        buttonCheckInput.setOnClickListener(view -> checkInput());
    }

    private void checkInput() {
        Log.d(TAG, "TAG - MainActivity - checkInput()");

        if (editTextInputEmail != null && textViewResult != null) {
            if (Patterns.DOMAIN_NAME.matcher(editTextInputEmail.getText().toString()).matches()) {
                textViewResult.setText(getResources().getString(R.string.correctly));
                textViewResult.setTextColor(getResources().getColor(R.color.colorGold));
            } else {
                textViewResult.setText(getResources().getString(R.string.error));
                textViewResult.setTextColor(getResources().getColor(R.color.colorRed));
            }
        }
    }
}
