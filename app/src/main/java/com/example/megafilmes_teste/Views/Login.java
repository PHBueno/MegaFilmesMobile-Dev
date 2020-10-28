package com.example.megafilmes_teste.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.megafilmes_teste.R;

public class Login extends AppCompatActivity {
    private Button button;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = (Button) findViewById(R.id.buttonLogin);
        btnRegister = (Button) findViewById(R.id.buttonRegister);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainAct();
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