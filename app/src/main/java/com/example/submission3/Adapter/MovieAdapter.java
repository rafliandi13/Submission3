package com.example.submission3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.submission3.Detail.MovieDetail;
import com.example.submission3.POJO.Movie;
import com.example.submission3.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ListViewHolder> {
    private ArrayList<Movie> mData = new ArrayList<>();
    public void setData(ArrayList<Movie> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,   parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        TextView tvTitle,tvDescription;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_movie);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_des);
            itemView.setOnClickListener(this);
        }

        public void bind(Movie movie) {
            String url_image = "https://image.tmdb.org/t/p/w185/" + movie.getImage();
            tvTitle.setText(movie.getName());
            tvDescription.setText(movie.getDescription());

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(image);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Movie movie = mData.get(position);

            movie.setName(movie.getName());
            movie.setDescription(movie.getDescription());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), MovieDetail.class);
            moveWithObjectIntent.putExtra(MovieDetail.EXTRA_MOVIE, movie);
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }
}
