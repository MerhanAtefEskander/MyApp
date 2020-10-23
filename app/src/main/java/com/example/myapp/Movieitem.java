package com.example.myapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies_table")
public class Movieitem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "movie_name")
    private String name;
    @ColumnInfo(name = "movie_image")
    private String imageurl;


    public Movieitem(int id, String name, String imageurl) {
        this.id = id;
        this.name = name;
        this.imageurl = imageurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

}
