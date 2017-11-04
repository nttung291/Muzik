package com.example.nttungpc.muzik.network.retrofit;

import com.example.nttungpc.muzik.network.json_model.MainObjectJSON;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nttung PC on 10/12/2017.
 */

public interface GetTypeMusicService {
    @GET("api")
    Call<MainObjectJSON> getMusicType();
}
