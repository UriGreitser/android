package com.example.whatsapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class AddContact extends AppCompatActivity {
    private AppDB db;
    private ContactDao ContactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDB.class, "UsersDB")
                .allowMainThreadQueries().build();
        ContactDao = db.ContactDao();


        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(view -> {
        EditText etContent = findViewById(R.id.etContent);
        Contact contact = new Contact(0,etContent.getText().toString());
        ContactDao.insert(contact);
        finish();




    });
    }
}