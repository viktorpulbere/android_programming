package com.example.blockit.storage;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Exception {
    public Exception(String type, String value) {
        this.value = value;
        this.type = type;
        this.timestamp = new Date().toLocaleString();
    }

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "value")
    public String value;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "timestamp")
    public String timestamp;
}