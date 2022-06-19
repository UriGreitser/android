package com.example.whatsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomMessageAdapter extends ArrayAdapter<Message> {
    LayoutInflater inflater;
    AppDB db;
    ContactDao ContactDao;
    String ConnectedUsername;

    public CustomMessageAdapter(Context ctx, List<Message> messageArrayList) {
        super(ctx, R.layout.custom_list_text_msg, messageArrayList);
        db = AppDB.getDatabase(ctx.getApplicationContext());
        this.inflater = LayoutInflater.from(ctx);
    }

    public CustomMessageAdapter(Context ctx, List<Message> messageArrayList, String c) {
        super(ctx, R.layout.custom_list_text_msg, messageArrayList);
        db = AppDB.getDatabase(ctx.getApplicationContext());
        this.inflater = LayoutInflater.from(ctx);
        this.ConnectedUsername = c;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Message message = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_list_text_msg, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.profile_image);
        TextView content = convertView.findViewById(R.id.content);
        TextView time = convertView.findViewById(R.id.time);
        TextView sender = convertView.findViewById(R.id.sender);


        imageView.setImageResource(R.drawable.ic_pereson);
        content.setText(message.getContent());
        time.setText(message.getCreated());
        String contactID = message.getContactId();
        ContactDao = db.ContactDao();
        Contact c = ContactDao.get(contactID);
        if (message.isSent()) {
            sender.setText(ConnectedUsername);   //user sent the message
        } else {
            sender.setText(message.getContactId());
        }
//        time.setText(message.getLastdate());

        return convertView;
    }
}
