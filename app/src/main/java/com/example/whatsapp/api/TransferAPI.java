package com.example.whatsapp.api;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransferAPI {
    Retrofit retrofit;
    WebServerAPI WebServerAPI;

    public TransferAPI(String server) {
        String url = "http://" + server + "/";
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebServerAPI = retrofit.create(WebServerAPI.class);
    }

    public class TransferStruct {
        public String from;
        public String to;
        public String content;
        public TransferStruct(String from, String to, String content) {
            this.from = from;
            this.to = to;
            this.content = content;
        }
    }

    public void post(String username, String contactName, String content) {
        TransferStruct transfer = new TransferStruct(username, contactName, content);
        Call<Void> call = WebServerAPI.postTransfer(transfer);
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
}
