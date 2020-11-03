package com.example.megafilmes_teste.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    private Button button;
    private EditText email, firstname, lastname, password;
    private TextView login;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private ProgressBar ProgressBar;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.newEmail);
        firstname = findViewById(R.id.newFirstName);
        lastname = findViewById(R.id.newLastName);
        password = findViewById(R.id.editTextTextPassword);
        ProgressBar = findViewById(R.id.progressBar);
        login = findViewById(R.id.ComeLogin);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        button = (Button) findViewById(R.id.NewRegister);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NewEmail = email.getText().toString().trim();
                String NewPasswd = password.getText().toString().trim();
                final String fname = firstname.getText().toString();
                final String lname = lastname.getText().toString();

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
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("FirstName", fname);
                            user.put("LastName", lname);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "User profile was created");
                                }
                            });
                            GoLogin();
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

    /*public void GoMovies(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }*/

    public void GoLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}