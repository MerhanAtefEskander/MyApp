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

public class MoviesListFragment extends Fragment {
    ProgressBar progressBar;
    RecyclerView movieList;
    MovieAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_list, container, false);
        movieList = view.findViewById(R.id.movieList);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);


        ApiInteface apiInteface = ApiClient.getClient().create(ApiInteface.class);
        apiInteface.getMovies().enqueue(new Callback<List<Movieitem>>() {
            @Override
            public void onResponse(Call<List<Movieitem>> call, Response<List<Movieitem>> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    List<Movieitem> movieitems = response.body();
                    setupAdapter(movieitems,view.getContext());


                }
            }

            @Override
            public void onFailure(Call<List<Movieitem>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
        return view;
    }


    private void setupAdapter(List<Movieitem> list, Context context) {
         adapter = new MovieAdapter(list,context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        movieList.setLayoutManager(layoutManager);
        movieList.setAdapter(adapter);
    }
}
