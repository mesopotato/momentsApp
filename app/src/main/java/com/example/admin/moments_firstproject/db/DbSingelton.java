package com.example.admin.moments_firstproject.db;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DbSingelton {
    private static AppDatabase db;

    public static AppDatabase getInstance(Context context){
        if (db == null){
            db = Room.databaseBuilder(context,
                    AppDatabase.class, "myDB3").allowMainThreadQueries().build();
        }
        return db;
    }

}
