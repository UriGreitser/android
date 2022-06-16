package com.example.whatsapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddContact extends AppCompatActivity {
    private AppDB db;
    private ContactDao ContactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Button btnSave = findViewById(R.id.btnSave);
        db = AppDB.getDatabase(getApplicationContext());
        ContactDao = db.ContactDao();
        btnSave.setOnClickListener(view -> {
        EditText etContent = findViewById(R.id.etContent);
        Contact contact = new Contact();
        contact.setName(etContent.getText().toString());
        contact.setId(etContent.getText().toString());
        contact.setUserName(etContent.getText().toString());
        contact.setLast("last text");
        contact.setLastdate("last date");
        ContactDao.insert(contact);
        finish();
    });
    }
}