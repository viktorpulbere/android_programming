package com.example.onlineshop;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.room.Room;

public class Repository {
    private String DB_NAME = "users";
    private AppDatabase userDatabase;

    public Repository(Context context) {
        userDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public void insertTask(String username, String password) {
        User user = new User(username, password);

        insertTask(user);
    }

    public void insertTask(final User user) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    userDatabase.userDao().insert(user);
                    Log.d("SIGN UP", "Inserted successfully");
                    int y = 0;
                } catch (SQLiteConstraintException err) {
                    int x = 0;
                    Log.e("SIGN UP", err.getMessage());
                } finally {
                    return null;
                }
            }
        }.execute();
    }
}