package com.example.whatsapp.api;


import com.example.whatsapp.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServerAPI {
    @GET("api/User/{id}")
    Call<User> getUser(@Path("id") String id);
    @GET("api/User")
    Call <List<User>> getUsers();

    @POST("api/User")
    Call<Void> postUser(@Body User user);

}
