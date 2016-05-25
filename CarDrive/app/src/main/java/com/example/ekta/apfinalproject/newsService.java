package com.example.ekta.apfinalproject;

import com.example.ekta.apfinalproject.model.NewsResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by newuser on 4/4/16.
 */
public interface newsService {

    @GET("/svc/search/v2/articlesearch.json")
    Call<NewsResponse> articleSearch(@QueryMap Map<String, String> params);

}
