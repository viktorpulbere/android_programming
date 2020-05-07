package com.example.blockit.storage;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExceptionDao {
    @Query("SELECT * FROM exception where type LIKE :type ORDER BY timestamp DESC")
    List<Exception> getAll(String type);

    @Insert
    void insertOne(Exception exception);

    @Delete
    void delete(Exception exception);
}