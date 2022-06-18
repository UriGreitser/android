package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @NonNull
    @PrimaryKey
    private String Name;
    private String Nickname;
    private String Password;
    private String Server;
    private String Picture;

    public User() {
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getServer() {
        return Server;
    }

    public void setServer(String server) {
        Server = server;
    }

    @Override
    public String toString() {
        return "User{" +
                ", Name='" + Name + '\'' +
                ", Nickname='" + Nickname + '\'' +
                ", Password='" + Password + '\'' +
                ", Server='" + Server + '\'' +
                ", Picture='" + Picture + '\'' +
                '}';
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public User(String name, String nickname, String password, String server, String picture) {
        Name = name;
        Nickname = nickname;
        Password = password;
        Server = server;
        Picture = picture;
    }
}
