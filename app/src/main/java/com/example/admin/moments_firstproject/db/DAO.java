package com.example.admin.moments_firstproject.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DAO {
    @Query("SELECT * FROM eintrag ORDER BY date DESC")
    List<Eintrag> getAll();

    @Query("SELECT * FROM eintrag WHERE uid= (select max(uid) from eintrag)")
    Eintrag getLast();

    @Query("SELECT * FROM eintrag WHERE uid IN (:userIds)")
    List<Eintrag> loadAllByIds(int[] userIds);

    //@Query("SELECT * FROM eintrag WHERE eingabe LIKE :first AND " +
    //      "last_name LIKE :last LIMIT 1")
    //User findByName(String first, String last);

    @Insert
    void insertAll(Eintrag... eintrag);

    @Delete
    void delete(Eintrag eintrag);

    @Query("Delete from eintrag")
    void drop();
}
