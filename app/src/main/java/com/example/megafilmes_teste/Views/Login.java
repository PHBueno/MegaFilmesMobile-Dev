package com.example.megafilmes_teste.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class Login extends AppCompatActivity {
    private Button btnLogin;
    private TextView btnRegister;
    private FirebaseAuth fAuth;
    private EditText email;
    private EditText password;
    private ProgressBar ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        int movieId = 0;
        int serieId = 0;
        String path = "";

        Intent intent = getIntent();
        Uri uri = intent.getData();

        if (intent.ACTION_VIEW.equals(intent.getAction())) {
            String id = uri.getQueryParameter("id");
            path = uri.getPath().split("/")[1];

            if (path.equals("serie")) {
                serieId = Integer.parseInt(id);
            } else {
                movieId = Integer.parseInt(id);
            }

        }


        ProgressBar = findViewById(R.id.progressBar);
        email = findViewById(R.id.editTextLogin);
        password = findViewById(R.id.editTextTextPassword);
        fAuth = FirebaseAuth.getInstance();
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnRegister = findViewById(R.id.buttonRegister);

        final int finalMovieId = movieId;
        final int finalSerieId = serieId;
        final String finalPath = path;
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserEmail = email.getText().toString().trim();
                String UserPasswd = password.getText().toString().trim();

                if(TextUtils.isEmpty(UserEmail)){
                    email.setError("The Email is required!");
                    return;
                }
                if(TextUtils.isEmpty(UserPasswd)){
                    password.setError("The Password is required!");
                    return;
                }
                if(UserPasswd.length() < 6){
                    password.setError("Password must be longer than 6 characters");
                }

                ProgressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(UserEmail, UserPasswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Wellcome", Toast.LENGTH_LONG).show();

                            if (finalPath.equals("filme")) {
                                Intent intent = new Intent(Login.this, OverviewMovie.class);
                                intent.putExtra(OverviewMovie.MOVIE_ID, finalMovieId);
                                startActivity(intent);
                                return;
                            }

                            if (finalPath.equals("serie")) {
                                Intent intent = new Intent(Login.this, OverviewSeries.class);
                                intent.putExtra(OverviewSeries.SERIE_ID, finalSerieId);
                                startActivity(intent);
                                return;
                            }

                            openMainAct();

                        } else{
                            Toast.makeText(Login.this, "Error " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterAct();
            }
        });
    }
    public void openMainAct(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void openRegisterAct(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }


}