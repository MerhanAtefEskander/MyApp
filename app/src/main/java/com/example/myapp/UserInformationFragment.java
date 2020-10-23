package com.example.myapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class UserInformationFragment extends Fragment {
    TextView textView;
    AppDatabase appDatabase;
    MoviesDao moviesDao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_information,container,false);
        appDatabase = AppDatabase.getInstance(view.getContext());
        moviesDao = appDatabase.moviesDao();
        textView = view.findViewById(R.id.frag_user_fav_count_txt);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

              textView.setText(moviesDao.getCount()+"");

            }

        });
        thread.start();
//        userimage=view.findViewById(R.id.user_image);
//        Picasso.get().load(R.drawable.images).into(userimage);

        //        userimage.setImageResource(R.drawable.ic_user);

        return view;
    }

}