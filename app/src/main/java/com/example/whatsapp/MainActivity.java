package com.example.whatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
private AppDB db;
private UserDao UserDao;
private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AppDB.getDatabase(getApplicationContext());
        UserDao = db.UserDao();
        //register button
    Button btnRegister = findViewById(R.id.btnRegister);
    btnRegister.setOnClickListener(v-> {
        startActivity(new Intent(MainActivity.this, Register.class));
    });
    //login button
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v-> {
            flag = false;
            EditText username = findViewById(R.id.username);
            EditText password = findViewById(R.id.password);
            String Username = username.getText().toString();
            String Password = password.getText().toString();
            TextView UserNotFound = findViewById(R.id.UserNotFound);
            TextView WrongPassword = findViewById(R.id.WrongPassword);
            UserNotFound.setVisibility(TextView.INVISIBLE);
            WrongPassword.setVisibility(TextView.INVISIBLE);
            User u = UserDao.getName(Username);
            if (u == null) {
                flag = true;
                UserNotFound.setVisibility(TextView.VISIBLE);
                return;
            }
            if (!Password.equals(u.getPassword())) {
                flag = true;
                WrongPassword.setVisibility(TextView.VISIBLE);
            }
            if (!flag) {
                flag = false;
                startActivity(new Intent(MainActivity.this, ContactList.class));
            }
        });

    }
}