package com.example.myapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movieitem.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase instance;
    public abstract MoviesDao moviesDao();
    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,AppDatabase.class,"movies_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
