package com.example.whatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp.api.ContactAPI;
import com.example.whatsapp.api.InvitationsAPI;

public class AddContact extends AppCompatActivity {
    private AppDB db;
    private ContactDao ContactDao;
    private UserDao UserDao;
    private Intent CurrentIntent;
    private String ConnectedUsername;
    private ContactAPI contactAPI;
    private InvitationsAPI invitationsAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        CurrentIntent = getIntent();
        ConnectedUsername = CurrentIntent.getStringExtra("username");
        this.contactAPI = new ContactAPI(this.ConnectedUsername);
        Button btnSave = findViewById(R.id.btnSave);
        db = AppDB.getDatabase(getApplicationContext());
        ContactDao = db.ContactDao();
        UserDao = db.UserDao();

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
        invitationsAPI = new InvitationsAPI(contact.getServer());
        ContactDao.insert(contact);
        contactAPI.post(contact);
        invitationsAPI.post(ConnectedUsername, contact.getId(), "10.0.2.2:7092");
        User u = UserDao.get(nickname.getText().toString());
        if (u != null) {
            Contact c = new Contact();
            c.setId(ConnectedUsername);
            c.setLast(contact.getLast());
            c.setLastdate(contact.getLastdate());
            c.setCountMessages(contact.getCountMessages());
            c.setUserName(u.getName());
            c.setServer(u.getServer());
            c.setName(u.getNickname());
            ContactDao.insert(c);
        }

        finish();
    });
    }
}