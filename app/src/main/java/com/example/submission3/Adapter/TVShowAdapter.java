package com.example.submission3.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submission3.Detail.MovieDetail;
import com.example.submission3.Detail.TVShowDetail;
import com.example.submission3.POJO.Movie;
import com.example.submission3.POJO.TVShow;
import com.example.submission3.R;

import java.util.ArrayList;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.ListViewHolder> {
    private ArrayList<TVShow> mData = new ArrayList<>();
    public void setData(ArrayList<TVShow> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TVShowAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tvshow,   parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowAdapter.ListViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView tvTitle,tvDescription;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_tvshow);
            tvTitle = itemView.findViewById(R.id.tv_title_tvshow);
            tvDescription = itemView.findViewById(R.id.tv_des_tvshow);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            TVShow tvShow = mData.get(position);

            tvShow.setName(tvShow.getName());
            tvShow.setDescription(tvShow.getDescription());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), TVShowDetail.class);
            moveWithObjectIntent.putExtra(TVShowDetail.EXTRA_MOVIE, tvShow);
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
        public void bind(TVShow tvShow) {
            String url_image = "https://image.tmdb.org/t/p/w185/" + tvShow.getImage();
            tvTitle.setText(tvShow.getName());
            tvDescription.setText(tvShow.getDescription());

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(image);
        }
    }
}
