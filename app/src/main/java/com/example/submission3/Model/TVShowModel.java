package com.example.submission3.Model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.submission3.POJO.Movie;
import com.example.submission3.POJO.TVShow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TVShowModel extends ViewModel {
    private static final String API_KEY = "713e1a940460cb23d84c197a5314baa7";
    private MutableLiveData<ArrayList<TVShow>> listvshow = new MutableLiveData<>();
    public void setTVshow(final String tvShow) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TVShow> listItems = new ArrayList<>();

        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" +API_KEY+ "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject weather = list.getJSONObject(i);
                        TVShow Tvshow = new TVShow();
                        Tvshow.setName(weather.getString("name"));
                        Tvshow.setImage(weather.getString("poster_path"));
                        Tvshow.setDescription(weather.getString("overview"));
                        Tvshow.setDuration(weather.getString("popularity"));
                        Tvshow.setGendre(weather.getString("vote_count"));
                        Tvshow.setRevenue(weather.getString("vote_average"));
                        listItems.add(Tvshow);
                    }
                    listvshow.postValue(listItems);
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
    public LiveData<ArrayList<TVShow>> getTVShow(){
        return listvshow;
    }
}
