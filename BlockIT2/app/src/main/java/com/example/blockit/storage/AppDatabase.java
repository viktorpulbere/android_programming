package com.example.blockit.storage;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Exception.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExceptionDao exceptionDao();
}