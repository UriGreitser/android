package com.example.whatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        FloatingActionButton btnAddContact = findViewById(R.id.btnAddContact);
        btnAddContact.setOnClickListener(v-> {
            startActivity(new Intent(ContactList.this, AddContact.class));

        });

//        db = Room.databaseBuilder(getApplicationContext(),
//                        AppDB.class, "ContacsDB")
//                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
//        contactDao = db.ContactDao();
//
        db = AppDB.getDatabase(getApplicationContext());
        ContactDao = db.ContactDao();
        Contact c = new Contact();
        c.setLast("last");
        c.setServer("server");
        c.setLastdate("lastdate");
        c.setName("name");
        c.setId("id");
        contacts = ContactDao.index();
        listView = findViewById(R.id.list_view);
        adapter = new CustomListAdapter(getApplicationContext(), contacts);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener((adapterView,view,i,l)->{
            Intent intent = new Intent(ContactList.this, SpecificChat.class);
            intent.putExtra("id", contacts.get(i).getId());
            startActivity(intent);
        });
        listView.setOnItemLongClickListener((adapterView, view,i,l) -> {
            Contact contact = contacts.remove(i);
            ContactDao.delete(contact);
            adapter.notifyDataSetChanged();
            return true;
        });
        Button btn = findViewById(R.id.button);
        btn.setVisibility(Button.INVISIBLE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        contacts.clear();
        contacts.addAll(ContactDao.index());
        adapter.notifyDataSetChanged();
    }

}