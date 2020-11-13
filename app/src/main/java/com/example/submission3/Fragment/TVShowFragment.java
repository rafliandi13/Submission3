package com.example.submission3.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.submission3.Adapter.MovieAdapter;
import com.example.submission3.Adapter.TVShowAdapter;
import com.example.submission3.Model.MovieModel;
import com.example.submission3.Model.TVShowModel;
import com.example.submission3.POJO.Movie;
import com.example.submission3.POJO.TVShow;
import com.example.submission3.R;

import java.util.ArrayList;

public class TVShowFragment extends Fragment {
    private TVShowAdapter adapter;
    private ProgressBar progressBar;
    private TVShowModel tvShowModel;
    public TVShowFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new TVShowAdapter();
        View view = inflater.inflate(R.layout.fragment_tvshow,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_tvshow);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        tvShowModel= ViewModelProviders.of(this).get(TVShowModel.class);
        tvShowModel.getTVShow().observe(this, getTVshow);
        tvShowModel.setTVshow("EXTRA_MOVIE");

        showLoading(true);

        return view;
    }
    private Observer<ArrayList<TVShow>> getTVshow = new Observer<ArrayList<TVShow>>() {
        @Override
        public void onChanged(ArrayList<TVShow> Tvshow) {
            if (Tvshow != null) {
                adapter.setData(Tvshow);
            }
            showLoading(false);

        }
    };
    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
