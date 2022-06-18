package com.example.whatsapp.api;

import android.util.Log;

import com.example.whatsapp.Message;
import com.example.whatsapp.MyApplication;
import com.example.whatsapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageAPI {
    Retrofit retrofit;
    WebServerAPI WebServerAPI;
    List<Message> messages;

    public MessageAPI(){
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebServerAPI = retrofit.create(WebServerAPI.class);
    }

    public MessageAPI(List<Message> messages){
        this.messages = messages;
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebServerAPI = retrofit.create(WebServerAPI.class);
    }

    public void post(Message message, String username) {
        Call<Void> call = WebServerAPI.postMessage(username, message.getContactId(), message.getContent());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                if (response.isSuccessful()) {
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("UserAPI", "onFailure: " + t.getMessage());
            }
        });
    }

    public void get(String username, String contactId) {
        Call<List<Message>> call = WebServerAPI.getMessages(username,contactId);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, retrofit2.Response<List<Message>> response) {
                if (response.isSuccessful()) {
                    messages.clear();
                    messages.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Log.d("UserAPI", "onFailure: " + t.getMessage());
            }
        });
    }

}
