package com.example.whatsapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    @PrimaryKey(autoGenerate= true)
    private int fakeID;
    private String id;
    private String name;
    private String server;
    private String last;
    private String lastdate;
    private int countMessages;
    private String UserName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFakeID() {
        return fakeID;
    }

    public void setFakeID(int fakeID) {
        this.fakeID = fakeID;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public int getCountMessages() {
        return countMessages;
    }

    public void setCountMessages(int countMessages) {
        this.countMessages = countMessages;
    }

    public String getUserName() {
        return UserName;
    }

    public Contact() {
    }

    public Contact(String id, String name, String server, String last, String lastdate, int countMessages, String userName) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastdate = lastdate;
        this.countMessages = countMessages;
        UserName = userName;
    }

    public Contact(int fakeID, String id) {
        this.fakeID = fakeID;
        this.id = id;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "fakeID=" + fakeID +
                ", id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", server='" + server + '\'' +
//                ", last='" + last + '\'' +
//                ", lastdate='" + lastdate + '\'' +
//                ", countMessages=" + countMessages +
//                ", UserName='" + UserName + '\'' +
                '}';
    }
}
