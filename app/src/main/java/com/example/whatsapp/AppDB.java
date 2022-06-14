package com.example.whatsapp;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class, User.class, Message.class}, version = 2)
public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao ContactDao();
    public abstract UserDao UserDao();
    public abstract MessageDao MessageDao();
}