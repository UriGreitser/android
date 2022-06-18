package com.example.whatsapp.api;

import android.util.Log;

import com.example.whatsapp.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {
    private List<User> users;
    Retrofit retrofit;
    WebServerAPI WebServerAPI;

    public UserAPI(List<User> users) {                        //MutableLiveData<List<User>> UserListData, UserDao dao
        this.users = users;

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:7092/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebServerAPI = retrofit.create(WebServerAPI.class);
    }

    public UserAPI(){
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebServerAPI = retrofit.create(WebServerAPI.class);
    }

    public void get() {
        Call<List<User>> call = WebServerAPI.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {
                if (response.isSuccessful()) {
                    users.clear();
                    users.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("UserAPI", "onFailure: " + t.getMessage());
            }
        });
    }

    public List<User> getUsers() {
        return users;
    }

    public void post(User user) {
        Call<Void> call = WebServerAPI.postUser(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                if (response.isSuccessful()) {
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("UserAPI", "onFailure: " + t.getMessage());
            }
        });
    }

    public void getUser(String id) {
        Call<User> call = WebServerAPI.getUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    users.clear();
                    users.add(user);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("UserAPI", "onFailure: " + t.getMessage());
            }
        });
    }
}