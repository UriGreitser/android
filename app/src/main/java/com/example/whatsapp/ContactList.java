package com.example.whatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ContactList extends AppCompatActivity {
    private List<Contact> contacts;
    private AppDB db;
    private ContactDao ContactDao;
    private ListView listView;
    private CustomListAdapter adapter;
    private Intent CurrentIntent;
    private String ConnectedUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        FloatingActionButton btnAddContact = findViewById(R.id.btnAddContact);
        btnAddContact.setOnClickListener(v-> {
            Intent intent2 = new Intent(ContactList.this, AddContact.class);
            intent2.putExtra("username",CurrentIntent.getStringExtra("username"));
            startActivity(intent2);
        });

        CurrentIntent = getIntent();
        db = AppDB.getDatabase(getApplicationContext());
        ContactDao = db.ContactDao();
        ConnectedUsername = CurrentIntent.getStringExtra("username");
        contacts = ContactDao.indexUsers(ConnectedUsername);
        listView = findViewById(R.id.list_view);
        adapter = new CustomListAdapter(getApplicationContext(), contacts);
        listView.setAdapter(adapter);
        listView.setClickable(true);


        listView.setOnItemClickListener((adapterView,view,i,l)->{
            Intent intent = new Intent(ContactList.this, SpecificChat.class);
            intent.putExtra("username",CurrentIntent.getStringExtra("username"));
            intent.putExtra("id", contacts.get(i).getId());
            startActivity(intent);
        });

        listView.setOnItemLongClickListener((adapterView, view,i,l) -> {
            Contact contact = contacts.remove(i);
            ContactDao.delete(contact);
            adapter.notifyDataSetChanged();
            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        contacts.clear();
        contacts.addAll(ContactDao.indexUsers(ConnectedUsername));
        int x = contacts.size();
        adapter.notifyDataSetChanged();
    }

}