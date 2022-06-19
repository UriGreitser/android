package com.example.whatsapp.api;

import android.util.Log;

import com.example.whatsapp.BaseUrl;
import com.example.whatsapp.User;
import com.example.whatsapp.UserDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {
    private List<User> users;
    private UserDao dao;
    Retrofit retrofit;
    WebServerAPI WebServerAPI;

    public UserAPI(List<User> users, UserDao dao) {                        //MutableLiveData<List<User>> UserListData, UserDao dao
        this.users = users;
        this.dao = dao;

        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.BASE_URL)
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
                    Log.d("test1", users.toString());
//                    dao.insert(users.toArray(new User[0]));
//                    UserListData.setValue(users);
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
}
//
//        call.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//
//                List<User> users= response.body();
//                Log.d("UserAPI",   response.body().toString());
//
//
////                new Thread(() -> {
////                    dao.clear();
////                    dao.insertList(response.body());
////                    UserListData.UserValue(dao.get());
////                }).start();
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//            }
//        });
//    }
//}