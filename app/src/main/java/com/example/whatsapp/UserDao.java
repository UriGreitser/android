package com.example.whatsapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> index();


    @Query("SELECT * FROM User WHERE fakeID = :fakeID")
    User get(int fakeID);


    @Query("SELECT * FROM User WHERE Name = :name")
     User getName(String name);

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);



}
