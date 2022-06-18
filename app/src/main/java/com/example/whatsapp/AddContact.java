package com.example.whatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddContact extends AppCompatActivity {
    private AppDB db;
    private ContactDao ContactDao;
    private Intent CurrentIntent;
    private String ConnectedUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        CurrentIntent = getIntent();
        ConnectedUsername = CurrentIntent.getStringExtra("username");
        Button btnSave = findViewById(R.id.btnSave);
        db = AppDB.getDatabase(getApplicationContext());
        ContactDao = db.ContactDao();

        btnSave.setOnClickListener(view -> {
        EditText nickname = findViewById(R.id.NewContactNickname);
        EditText ID = findViewById(R.id.NewContactID);
        EditText server = findViewById(R.id.NewContactServer);
        Contact contact = new Contact();
        contact.setName(nickname.getText().toString());
        contact.setId(ID.getText().toString());
        contact.setServer(server.getText().toString());
        contact.setUserName(ConnectedUsername);
        contact.setLast("last text");
        contact.setLastdate("last date");
        ContactDao.insert(contact);
        finish();
    });
    }
}