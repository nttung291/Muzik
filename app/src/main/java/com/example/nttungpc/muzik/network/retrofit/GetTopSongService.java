package com.example.nttungpc.muzik.network.retrofit;

import com.example.nttungpc.muzik.network.json_model.TopSongResponseJSON;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Nttung PC on 10/24/2017.
 */

public interface GetTopSongService {
    @GET("https://itunes.apple.com/us/rss/topsongs/limit=50/genre={id}/explicit=true/json")
    Call<TopSongResponseJSON> getTopSongs(@Path("id") String id);

}
