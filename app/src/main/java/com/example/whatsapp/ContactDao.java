package com.example.whatsapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM Contact")
    List<Contact> index();

    @Query("SELECT * FROM Contact WHERE UserName = :Username")
    List<Contact> indexUsers(String Username);

    @Query("SELECT * FROM Contact WHERE id = :id")
    Contact get(String id);

    @Insert
    void insert(Contact... contacts);

    @Update
    void update(Contact... contacts);

    @Delete
    void delete(Contact... contacts);
}
