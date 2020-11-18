package com.neo.androidpagingurgent.network;

import com.neo.androidpagingurgent.network.model.StackApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("answers")
    Call<StackApiResponse> getAnswers(@Query("page") int page,
                                      @Query("pagesize") int size,
                                      @Query("site") String site);
}
