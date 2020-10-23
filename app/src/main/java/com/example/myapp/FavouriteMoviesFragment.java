package com.example.myapp;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteMoviesFragment extends Fragment {
    AppDatabase appDatabase;
    MoviesDao moviesDao;
    RecyclerView movieList;
    FavouriteAdapter favouriteAdapter;
    List<Movieitem> movieitems;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_favourite_movies,container,false);

        appDatabase = AppDatabase.getInstance(view.getContext());
        movieList = view.findViewById(R.id.movieList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        movieList.setLayoutManager(layoutManager);
        moviesDao = appDatabase.moviesDao();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                movieitems=moviesDao.getAll();
                favouriteAdapter=new FavouriteAdapter(movieitems,view.getContext());
                movieList.setAdapter(favouriteAdapter);
            }
        });
        thread.start();
        return view;
    }

}