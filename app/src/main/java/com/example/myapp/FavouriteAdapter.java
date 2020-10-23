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

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MovieViewHolder> {
    List<Movieitem> movies;
    AppDatabase appDatabase;
    MoviesDao moviesDao;

    public FavouriteAdapter(List<Movieitem> movies, Context context) {
        this.movies = movies;
        appDatabase = AppDatabase.getInstance(context);
        moviesDao = appDatabase.moviesDao();
    }

    @NonNull
    @Override
    public FavouriteAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.MovieViewHolder holder, int position) {
        Movieitem movieitem = movies.get(position);
        holder.title.setText(movieitem.getName());
        Picasso.get().load(movieitem.getImageurl()).into(holder.movieicon);
        holder.likeButton.setLiked(true);
        holder.likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                moviesDao.deleteByName(movieitem.getName());
                movies.remove(position);
                notifyItemRemoved(position);
                Log.e("delete from database :", "true");
            }
        });

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