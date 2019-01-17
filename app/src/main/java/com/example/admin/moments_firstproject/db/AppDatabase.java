package com.example.admin.moments_firstproject.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Eintrag.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DAO dao();
}
