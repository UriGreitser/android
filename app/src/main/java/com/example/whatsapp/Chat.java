package com.example.whatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
//no need
public class Chat extends AppCompatActivity {

    private List<Contact> contacts;
    private AppDB db;
    private ContactDao contactDao;
    private ArrayAdapter<Contact> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        FloatingActionButton btnAddContact = findViewById(R.id.btnAddContact);
        btnAddContact.setOnClickListener(v-> {
            startActivity(new Intent(Chat.this, AddContact.class));

        });
        db = Room.databaseBuilder(getApplicationContext(),
                        AppDB.class, "ContacsDB")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        contactDao = db.ContactDao();

        contacts = new ArrayList<>();

        ListView lvContacts = findViewById(R.id.lvContacts);
        adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contacts);
        lvContacts.setAdapter(adapter);
        contacts.addAll(contactDao.index());
        lvContacts.setOnItemLongClickListener((adapterView, view,i,l) -> {
            Contact contact = contacts.remove(i);
            contactDao.delete(contact);
            adapter.notifyDataSetChanged();
            return true;
        });
        lvContacts.setOnItemClickListener((adapterView,view,i,l)->{
            Intent intent = new Intent(Chat.this, SpecificChat.class);
            intent.putExtra("id", contacts.get(i).getId());
            startActivity(intent);
        });
    }
    protected void onResume() {
        super.onResume();
        contacts.clear();
        contacts.addAll(contactDao.index());
        adapter.notifyDataSetChanged();
    }
}