package com.example.submission3.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.submission3.POJO.TVShow;
import com.example.submission3.R;

public class TVShowDetail extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    private ProgressBar progressBar;
    TextView tvTitle,tvDescription,tvRevenue,tvGendre,tvDuration;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_detail);

        tvTitle = findViewById(R.id.tv_title_detail);
        tvDescription = findViewById(R.id.tv_sinopsis);
        tvDuration = findViewById(R.id.tv_duration);
        tvRevenue = findViewById(R.id.tv_director);
        tvGendre = findViewById(R.id.tv_genre);
        imageView = findViewById(R.id.iv_detail);
        progressBar = findViewById(R.id.pb_detail_tvshow);

        progressBar.setVisibility(View.VISIBLE);
        TVShow tvShow = getIntent().getParcelableExtra(EXTRA_MOVIE);

        String url_image = "https://image.tmdb.org/t/p/w185/" + tvShow.getImage();

        tvTitle.setText(tvShow.getName());
        tvRevenue.setText(tvShow.getRevenue());
        tvGendre.setText(tvShow.getGendre());
        tvDescription.setText(tvShow.getDescription());
        tvDuration.setText(tvShow.getDuration());
        Glide.with(TVShowDetail.this)
                .load(url_image)
                .placeholder(R.color.colorAccent)
                .dontAnimate()
                .into(imageView);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
