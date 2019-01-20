package com.example.admin.moments_firstproject.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Eintrag {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "titel")
    private String titel;

    @ColumnInfo(name = "detail")
    private String detail;

    @ColumnInfo(name = "imgPath")
    private String imgPath;

    @ColumnInfo(name = "long")
    private String lng;

    @ColumnInfo(name = "lat")
    private String lat;

    @ColumnInfo(name = "date")
    private String date;

    public Eintrag(String titel, String detail, String imgPath, String lng, String lat, String date) {
        this.titel = titel;
        this.detail = detail;
        this.imgPath = imgPath;
        this.lng = lng;
        this.lat = lat;
        this.date = date;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
