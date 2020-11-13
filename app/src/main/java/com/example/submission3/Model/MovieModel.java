package com.example.submission3.Model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.submission3.POJO.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieModel extends ViewModel {
    private static final String API_KEY = "713e1a940460cb23d84c197a5314baa7";
    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();

    public void setMovies(final String movies) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> listItems = new ArrayList<>();

        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" +API_KEY+ "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject weather = list.getJSONObject(i);
                        Movie movieItems = new Movie();
                        movieItems.setName(weather.getString("title"));
                        movieItems.setImage(weather.getString("poster_path"));
                        movieItems.setDescription(weather.getString("overview"));
                        movieItems.setDuration(weather.getString("popularity"));
                        movieItems.setGendre(weather.getString("vote_count"));
                        movieItems.setRevenue(weather.getString("vote_average"));
                        listItems.add(movieItems);
                    }
                    listMovies.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }
    public LiveData<ArrayList<Movie>> getMovie(){
        return listMovies;
    }
}
