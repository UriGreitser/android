package com.example.whatsapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            EditText etName = findViewById(R.id.etName);
            BaseUrl.setBaseUrl(etName.getText().toString());
            finish();
        });
    }
}