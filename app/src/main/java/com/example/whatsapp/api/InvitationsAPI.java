package com.example.whatsapp.api;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InvitationsAPI {
    Retrofit retrofit;
    WebServerAPI WebServerAPI;

    public InvitationsAPI(String server) {
        String url = "http://" + server + "/";
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebServerAPI = retrofit.create(WebServerAPI.class);
    }

    public class InvitationStruct {
        public String from;
        public String to;
        public String server;
        public InvitationStruct(String from, String to, String server) {
            this.from = from;
            this.to = to;
            this.server = server;
        }
    }

    public void post(String username, String contactName, String server) {
        InvitationStruct invitation = new InvitationStruct(username, contactName, server);
        Call<Void> call = WebServerAPI.postInvitation(invitation);
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
