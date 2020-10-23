package com.example.myapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (Movieitem movieitem);

    @Delete
    void delete(Movieitem movieitem);

    @Query("DELETE FROM movies_table WHERE movie_name=:movie_name")
    void deleteByName(String movie_name);

    @Update
    void update (Movieitem movieitem);

    @Query("SELECT COUNT(*) FROM movies_table")
    int getCount();


    @Query("Select * FROM movies_table")
    List<Movieitem> getAll() ;

    @Query("Select * FROM movies_table WHERE movie_name=:movie_name")
    Movieitem getItemByName(String movie_name) ;

    @Query("DELETE FROM movies_table")
    void deleteAll();



}
