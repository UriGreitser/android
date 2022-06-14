package com.example.whatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
private AppDB db;
private ContactDao ContactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         db = Room.databaseBuilder(getApplicationContext(),
                        AppDB.class, "UsersDB")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
         ContactDao = db.ContactDao();


       //  handleSave();







        //register button
    Button btnRegister = findViewById(R.id.btnRegister);
    btnRegister.setOnClickListener(v-> {
        startActivity(new Intent(MainActivity.this, Register.class));
    });
    //login button
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v-> {
            startActivity(new Intent(MainActivity.this, Chat.class));
        });

    }
}