package com.example.myapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInteface {
    @GET("demos/marvel/")   // end point
  Call<List<Movieitem>> getMovies();

}
