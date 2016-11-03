package com.netforceinfotech.recycle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by xyz on 11/1/2016.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    // initialize the Movie Adapter...
    private List<JsonDataMovie> movielist;
    private Context context;

    public MovieAdapter(List<JsonDataMovie> movielist, Context context) {
        this.movielist = movielist;
        this.context = context;
    }



    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// inflate or render the item_list_view layout...

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_row_movie_layout, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {

        holder.title.setText(movielist.get(position).getTitle());
        holder.releasedate.setText(movielist.get(position).getReleaseDate());
        holder.description.setText(movielist.get(position).getOverview());
        holder.rating.setText(movielist.get(position).getVoteAverage().toString());

        Glide.with(context).load(movielist.get(position).getPosterPath()).error(R.drawable.testimage).into(holder.imageurl);


        // Onclick....

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
                Intent intent = new Intent(context, MovieDetails.class);
                Bundle bundle=new Bundle();
                bundle.putInt("id",movielist.get(position).getId());
                bundle.putString("title",movielist.get(position).getTitle());
                bundle.putString("imagepath",movielist.get(position).getPosterPath());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        int value = 0;
        try {

           value =  movielist.size();
        }catch (Exception e){
            
            e.fillInStackTrace();
        }
      return value;
    }
}
