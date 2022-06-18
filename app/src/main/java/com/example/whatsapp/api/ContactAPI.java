package com.example.whatsapp.api;

import android.util.Log;

import com.example.whatsapp.Contact;
import com.example.whatsapp.MyApplication;
import com.example.whatsapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactAPI {
    private String loggedUser;
    private List<Contact> contacts;
    Retrofit retrofit;
    WebServerAPI WebServerAPI;

    public ContactAPI(List<Contact> contacts, String loggedUser) {                        //MutableLiveData<List<User>> UserListData, UserDao dao
        this.contacts = contacts;
        this.loggedUser = loggedUser;

        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebServerAPI = retrofit.create(WebServerAPI.class);
    }

    public ContactAPI(String loggedUser) {                        //MutableLiveData<List<User>> UserListData, UserDao dao
        this.loggedUser = loggedUser;

        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebServerAPI = retrofit.create(WebServerAPI.class);
    }

    public void get() {
        Call<List<Contact>> call = WebServerAPI.getContacts(loggedUser);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, retrofit2.Response<List<Contact>> response) {
                if (response.isSuccessful()) {
                    contacts.clear();
                    contacts.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Log.d("UserAPI", "onFailure: " + t.getMessage());
            }
        });
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public class ContactStruct {
        public String contact;
        public String nickName;
        public String server;
        public ContactStruct(String contact, String nickName, String server){
            this.contact = contact;
            this.nickName = nickName;
            this.server = server;
        }
    }

    public void post(Contact contact) {
        ContactStruct contactStruct = new ContactStruct(contact.getId(), contact.getName(), contact.getServer());
        Call<Void> call = WebServerAPI.postContact(this.loggedUser, contactStruct);
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

    public void delete(Contact contact) {
        Call<Void> call = WebServerAPI.deleteContact(this.loggedUser, contact.getId());
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

    public void getContact(String id) {
        Call<Contact> call = WebServerAPI.getContact(this.loggedUser, id);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, retrofit2.Response<Contact> response) {
                if (response.isSuccessful()) {
                }
            }

            public void onFailure(Call<Contact> call, Throwable t) {
                Log.d("UserAPI", "onFailure: " + t.getMessage());
            }
        });
    }
}