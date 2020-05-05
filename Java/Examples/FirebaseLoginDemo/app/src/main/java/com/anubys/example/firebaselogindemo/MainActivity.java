package com.anubys.example.firebaselogindemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 22.04.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.et_email)
    EditText editTextEmail;
    @Nullable
    @BindView(R.id.et_password)
    EditText editTextPassword;
    @Nullable
    @BindView(R.id.tv_output_text)
    TextView textViewOutput;
    @Nullable
    @BindView(R.id.btn_sign_in)
    Button btnSignIn;
    @Nullable
    @BindView(R.id.btn_sign_out)
    Button btnSignOut;
    @Nullable
    @BindView(R.id.btn_register)
    Button btnRegister;
    @Nullable
    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    private Unbinder unbinder;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "TAG - MainActivity - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new AuthStateListenerFirebase();

        setListener();
        hideProgressBar();
    }

    @Override
    protected void onResume() {
        Log.v(TAG, "TAG - MainActivity - onCreate()");
        super.onResume();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onPause() {
        Log.v(TAG, "TAG - MainActivity - onCreate()");
        super.onPause();
        if (authStateListener != null) {
            if (firebaseAuth != null) {
                firebaseAuth.removeAuthStateListener(authStateListener);
            }
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
    private void setListener() {
        Log.v(TAG, "TAG - MainActivity - setListener()");
        if (btnSignIn != null) {
            btnSignIn.setOnClickListener(v -> signIn());
        }
        if (btnSignOut != null) {
            btnSignOut.setOnClickListener(v -> signOut());
        }
        if (btnRegister != null) {
            btnRegister.setOnClickListener(v -> register());
        }
    }

    private void signIn() {
        Log.v(TAG, "TAG - MainActivity - signIn()");
        if (!validateEmailAdress() | !validatePassword()) {
            return;
        }

        String email = "";
        String password = "";
        if (editTextEmail != null && editTextPassword != null) {
            email = editTextEmail.getText().toString().trim();
            password = editTextPassword.getText().toString().trim();
        }

        showProgressBar();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListenerFirebaseSignIn());
    }

    private void signOut() {
        Log.v(TAG, "TAG - MainActivity - signOut()");
        firebaseAuth.signOut();
    }

    private void register() {
        Log.v(TAG, "TAG - MainActivity - register()");
        if (!validateEmailAdress() | !validatePassword()) {
            return;
        }

        String email = "";
        String password = "";
        if (editTextEmail != null && editTextPassword != null) {
            email = editTextEmail.getText().toString().trim();
            password = editTextPassword.getText().toString().trim();
        }

        showProgressBar();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListenerFirebaseCreate());
    }

    private void updateUI() {
        Log.v(TAG, "TAG - MainActivity - updateUI()");
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            if (textViewOutput != null) {
                textViewOutput.setText(getResources().getString(R.string.txt_user_signin_fail));
            }
        } else {
            if (textViewOutput != null) {
                textViewOutput.setText(firebaseUser.getEmail());
            }
        }
    }

    private void showProgressBar() {
        Log.v(TAG, "TAG - MainActivity - showProgressBar()");
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressBar() {
        Log.v(TAG, "TAG - MainActivity - hideProgressBar()");
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    private boolean validateEmailAdress() {
        Log.v(TAG, "TAG - MainActivity - validateEmailAdress()");

        if (editTextEmail != null) {
            String email = editTextEmail.getText().toString().trim();

            if (email.isEmpty()) {
                editTextEmail.setError(getResources().getString(R.string.txt_email_info));
                return (false);
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTextEmail.setError(getResources().getString(R.string.txt_email_fail));
                return (false);
            } else {
                editTextEmail.setError(null);
                return (true);
            }
        }

        return (false);
    }

    private boolean validatePassword() {
        Log.v(TAG, "TAG - MainActivity - validatePassword()");

        if (editTextPassword != null) {
            String password = editTextPassword.getText().toString().trim();

            if (password.isEmpty()) {
                editTextPassword.setError(getResources().getString(R.string.txt_password_info));
                return (false);
            } else if (password.length() < 5) {
                editTextPassword.setError(getResources().getString(R.string.txt_email_fail));
                return (true);
            } else {
                editTextPassword.setError(null);
                return (true);
            }
        }

        return (false);
    }


    //* ************************************************ *
    //*                 C L A S S E S                    *
    //* ************************************************ *
    private class AuthStateListenerFirebase implements FirebaseAuth.AuthStateListener {

        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            Log.v(TAG, "TAG - MainActivity - onAuthStateChanged()");
            updateUI();
        }
    }

    private class OnCompleteListenerFirebaseSignIn implements OnCompleteListener<AuthResult> {

        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            Log.v(TAG, "TAG - MainActivity - onComplete()");
            if (task.isSuccessful()) {
                hideProgressBar();
                Toast.makeText(getApplicationContext(), "Benutzer erfolgreich eingeloggt", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Fehler beim einloggen", Toast.LENGTH_LONG).show();
                hideProgressBar();
            }
        }
    }

    private class OnCompleteListenerFirebaseCreate implements OnCompleteListener<AuthResult> {

        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            Log.v(TAG, "TAG - MainActivity - onComplete()");
            if (task.isSuccessful()) {
                hideProgressBar();
                Toast.makeText(getApplicationContext(), "Benutzer erfolgreich erstellt", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Fehler beim erstellen passiert", Toast.LENGTH_LONG).show();
                hideProgressBar();
            }
        }
    }
}
