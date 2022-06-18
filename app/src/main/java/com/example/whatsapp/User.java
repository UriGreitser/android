package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class User {

    @NonNull
    @PrimaryKey
    private String name;
    private String nickname;
    private String password;
    private String server;
    private String picture;

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + name + '\'' +
                ", Nickname='" + nickname + '\'' +
                ", Password='" + password + '\'' +
                ", Server='" + server + '\'' +
                ", Picture='" + picture + '\'' +
                '}';
    }
//
//    public User(String name, String nickname, String password, String server, String picture) {
//        this.name = name;
//        this.nickname = nickname;
//        this.password = password;
//        this.server = server;
//        this.picture = picture;
//    }

    public User(String name) {
        this.name = name;
    }

    public User(@NonNull String name, String nickname, String password, String server, String picture, List<String> contacts) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.server = server;
        this.picture = picture;
    }

}
