package com.example.whatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class SpecificChat extends AppCompatActivity {
    private List<Message> messages;
    private AppDB db;
    private MessageDao MessageDao;
    private ContactDao ContactDao;
    private ListView listView;
    private CustomMessageAdapter adapter;
    private Intent CurrentIntent;
    private String ConnectedUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_chat);

        FloatingActionButton btnSendMessage = findViewById(R.id.btnSendMessage);

        CurrentIntent = getIntent();
        db = AppDB.getDatabase(getApplicationContext());
        MessageDao = db.MessageDao();
        ContactDao = db.ContactDao();
        ConnectedUsername = CurrentIntent.getStringExtra("username");
        messages = MessageDao.indexMessages(CurrentIntent.getStringExtra("id"));
        listView = findViewById(R.id.list_view);
        adapter = new CustomMessageAdapter(getApplicationContext(), messages);
        listView.setAdapter(adapter);
        listView.setClickable(true);

        btnSendMessage.setOnClickListener(view -> {
            EditText message = findViewById(R.id.textContent);
            Message message1 = new Message();
            message1.setContent(message.getText().toString());
            DateFormat df = new SimpleDateFormat("h:mm a");
            String date = df.format(Calendar.getInstance().getTime());
            message1.setCreated(date);
            message1.setSent(true);
            message1.setContactId(CurrentIntent.getStringExtra("id"));
            Contact c = db.ContactDao().get(CurrentIntent.getStringExtra("id"));
            ContactDao.delete(c);
            c.setLast(message.getText().toString());
            c.setLastdate(date);
            ContactDao.insert(c);
            MessageDao.insert(message1);
            adapter.notifyDataSetChanged();
        });

        listView.setOnItemLongClickListener((adapterView, view,i,l) -> {
            Message message = messages.remove(i);
            MessageDao.delete(message);
            adapter.notifyDataSetChanged();
            return true;
        });


    }

    protected void onRefresh() {
        super.onResume();
        messages.clear();
        messages.addAll(MessageDao.indexMessages(CurrentIntent.getStringExtra("id")));
        int x = messages.size();
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onResume() {
        super.onResume();
        messages.clear();
        messages.addAll(MessageDao.indexMessages(CurrentIntent.getStringExtra("id")));
        int x = messages.size();
        adapter.notifyDataSetChanged();
    }
}

