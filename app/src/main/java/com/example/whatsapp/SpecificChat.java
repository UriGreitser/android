package com.example.whatsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
    private EditText message;

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
        adapter = new CustomMessageAdapter(getApplicationContext(), messages,ConnectedUsername);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        TextView contactName = findViewById(R.id.contactName);
        contactName.setText(CurrentIntent.getStringExtra("id"));

        btnSendMessage.setOnClickListener(view -> {
            message = findViewById(R.id.textContent);
            User userCheck = db.UserDao().get(CurrentIntent.getStringExtra("id"));
            if(userCheck == null) {

            }
            else if (message.getText().toString().length() != 0 ) {
                Message message1 = new Message();
                Message message2 = new Message();
                message2.setSent(false);
                message1.setContent(message.getText().toString());
                DateFormat df = new SimpleDateFormat("h:mm a");
                String date = df.format(Calendar.getInstance().getTime());
                message2.setCreated(date);
                message2.setContent(message.getText().toString());
                message2.setContactId(ConnectedUsername);
                message1.setCreated(date);
                message1.setSent(true);
                message1.setContactId(CurrentIntent.getStringExtra("id"));
                Contact c = db.ContactDao().get(CurrentIntent.getStringExtra("id"));
                Contact c1 = db.ContactDao().get(ConnectedUsername);
                ContactDao.delete(c1);
                ContactDao.delete(c);
                c.setLast(message.getText().toString());
                c.setLastdate(date);
                c1.setLast(message.getText().toString());
                c1.setLastdate(date);
                ContactDao.insert(c1);
                ContactDao.insert(c);
                MessageDao.insert(message1);
                MessageDao.insert(message2);
                message.setText("");
//            listView = findViewById(R.id.list_view);
//            adapter = new CustomMessageAdapter(getApplicationContext(), messages);
//            listView.setAdapter(adapter);
//            listView.setClickable(true);
//            adapter.notifyDataSetChanged();
                onResume();
            }
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

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        messages.clear();
        messages.addAll(MessageDao.indexMessages(CurrentIntent.getStringExtra("id")));
        System.out.println("debug");
        adapter.notifyDataSetChanged();
        listView.setVisibility(View.VISIBLE);
    }
}

