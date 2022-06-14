package com.example.whatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    Button btnRegister = findViewById(R.id.btnRegister);
    btnRegister.setOnClickListener(v-> {
        startActivity(new Intent(MainActivity.this, Register.class));
    });

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v-> {
            startActivity(new Intent(MainActivity.this, Chat.class));
        });

    }
}