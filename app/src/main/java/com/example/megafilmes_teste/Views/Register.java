package com.example.megafilmes_teste.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.megafilmes_teste.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
     private Button button;
     private EditText email, username, password;
     private TextView login;
     private FirebaseAuth fAuth;
     private ProgressBar ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.newEmail);
        username = findViewById(R.id.newUser);
        password = findViewById(R.id.editTextTextPassword);
        ProgressBar = findViewById(R.id.progressBar);
        login = findViewById(R.id.ComeLogin);

        fAuth = FirebaseAuth.getInstance();

        button = (Button) findViewById(R.id.NewRegister);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NewEmail = email.getText().toString().trim();
                String NewPasswd = password.getText().toString().trim();

                if(TextUtils.isEmpty(NewEmail)){
                    email.setError("The Email is required!");
                    return;
                }
                if(TextUtils.isEmpty(NewPasswd)){
                    password.setError("The Password is required!");
                    return;
                }
                if(NewPasswd.length() < 6){
                    password.setError("Password must be longer than 6 characters");
                }

                ProgressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(NewEmail, NewPasswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_LONG).show();
                            GoMovies();
                        }else {
                            Toast.makeText(Register.this, "Error " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoLogin();
            }
        });
    }

    public void GoMovies(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void GoLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}