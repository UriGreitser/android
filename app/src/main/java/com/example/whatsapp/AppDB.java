package com.example.whatsapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class, User.class, Message.class}, version = 12)
public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao ContactDao();

    public abstract UserDao UserDao();

    public abstract MessageDao MessageDao();

    private static volatile AppDB noteRoomInstance;

    static AppDB getDatabase(final Context context) {
        if (noteRoomInstance == null) {
            synchronized (AppDB.class) {
                if (noteRoomInstance == null) {
                    noteRoomInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDB.class, "note_database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
                }
            }
        }
        return noteRoomInstance;
    }
}