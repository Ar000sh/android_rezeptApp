package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import Model.Benutzer;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button logBNT;
    private TextView register;
    private EditText username;
    private EditText logpassword;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logBNT = findViewById(R.id.button);
        username = findViewById(R.id.edittextUsername);
        logpassword = findViewById(R.id.edittextPassword);
        mAuth = FirebaseAuth.getInstance();
        logBNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    login();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    private void login() {
        String email = username.getText().toString().trim();
        String password = logpassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            username.setError("Email cant be left out");
            username.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            logpassword.setError("Password cant be left out");
            logpassword.requestFocus();
        } else {

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });



            }

        }
    }
