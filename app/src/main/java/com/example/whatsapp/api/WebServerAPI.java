package com.example.whatsapp.api;

import com.example.whatsapp.Contact;
import com.example.whatsapp.Message;
import com.example.whatsapp.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebServerAPI {

    //User Api --------------------------------------------------------------------------------------------------------
    @GET("api/User/{id}")
    Call<User> getUser(@Path("id") String id);

    @GET("api/User")
    Call <List<User>> getUsers();

    @POST("api/User")
    Call<Void> postUser(@Body User user);


    //Contact Api ---------------------------------------------------------------------------------------------------------
    @GET("api/contacts")
    Call<List<Contact>> getContacts(@Query("username") String username);

    @POST("api/contacts")
    Call<Void> postContact(@Query("username") String username , @Body ContactAPI.ContactStruct contact);

    @GET("api/contacts/{id}")
    Call<Contact> getContact(@Query("username") String username,@Path("id") String id);

    @DELETE("api/contacts/{id}")
    Call<Void> deleteContact(@Query("username") String username,@Path("id") String id);

    //Invitations Api ---------------------------------------------------------------------------------------------------------
    @POST("api/invitations")
    Call<Void> postInvitation(@Body InvitationsAPI.InvitationStruct invitation);

    //Transfer Api ---------------------------------------------------------------------------------------------------------
    @POST("api/transfer")
    Call<Void> postTransfer(@Body TransferAPI.TransferStruct transfer);

    //Messages Api ---------------------------------------------------------------------------------------------------------
    @GET("api/contacts/{id}/messages")
    Call<List<Message>> getMessages(@Query("username") String username, @Path("id") String id);

    @POST("api/messages/{id}")
    Call<Void> postMessage(@Query("username") String username, @Path("id") String id, @Body String content);



}
