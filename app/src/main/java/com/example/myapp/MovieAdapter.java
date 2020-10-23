package com.example.myapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    List<Movieitem> movies;
    AppDatabase appDatabase;
    MoviesDao moviesDao;

    public MovieAdapter(List<Movieitem> movies, Context context) {
        this.movies = movies;
        appDatabase = AppDatabase.getInstance(context);
        moviesDao = appDatabase.moviesDao();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movieitem movieitem = movies.get(position);
        holder.title.setText(movieitem.getName());
        Picasso.get().load(movieitem.getImageurl()).into(holder.movieicon);

        holder.likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                moviesDao.insert(movieitem);
                Log.e("add to database :", "true");
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                moviesDao.deleteByName(movieitem.getName());
                Log.e("delete from database :", "true");
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (moviesDao.getItemByName(movieitem.getName()) != null) {
                    holder.likeButton.setLiked(true);
                    Log.e(" item in database :", "true");
                } else {
                    holder.likeButton.setLiked(false);
                    Log.e(" item in database :", "false");
                }
            }
        }).start();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView movieicon;
        private LikeButton likeButton;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            movieicon = itemView.findViewById(R.id.movieicon);
            likeButton = itemView.findViewById(R.id.heart_button);


        }

    }

}
