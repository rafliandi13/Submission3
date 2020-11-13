package com.example.submission3.Detail;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.submission3.POJO.Movie;
import com.example.submission3.R;


public class MovieDetail extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    private ProgressBar progressBar;
    TextView tvTitle,tvDescription,tvRevenue,tvGendre,tvDuration;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        tvTitle = findViewById(R.id.tv_title_detail);
        tvDescription = findViewById(R.id.tv_sinopsis);
        tvDuration = findViewById(R.id.tv_duration);
        tvRevenue = findViewById(R.id.tv_director);
        tvGendre = findViewById(R.id.tv_genre);
        imageView = findViewById(R.id.iv_detail);
        progressBar = findViewById(R.id.pb_detail_movie);

        progressBar.setVisibility(View.VISIBLE);
        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        String url_image = "https://image.tmdb.org/t/p/w185/" + movie.getImage();

        tvTitle.setText(movie.getName());
        tvRevenue.setText(movie.getRevenue());
        tvGendre.setText(movie.getGendre());
        tvDescription.setText(movie.getDescription());
        tvDuration.setText(movie.getDuration());
        Glide.with(MovieDetail.this)
                .load(url_image)
                .placeholder(R.color.colorAccent)
                .dontAnimate()
                .into(imageView);
        progressBar.setVisibility(View.INVISIBLE);

    }

}
