package com.example.blockit.storage;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import java.util.List;

public class Repository {
    private String DB_NAME = "blockit";
    private AppDatabase blockitDatabase;

    public Repository(Context context) {
        blockitDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();;
    }

    public void insertException(String value, String type) {
        Exception exception = new Exception(value, type);
        insertTask(exception);
    }

    public List<Exception> getAll(String type) {
        return blockitDatabase.exceptionDao().getAll(type);
    }

    public void deleteOne(Exception exception) {
        blockitDatabase.exceptionDao().delete(exception);
    }

    public void insertTask(final Exception exception) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    blockitDatabase.exceptionDao().insertOne(exception);
                } catch (SQLiteConstraintException err) {
                    Log.e("SIGN UP", err.getMessage());
                } finally {
                    return null;
                }
            }
        }.execute();
    }
}