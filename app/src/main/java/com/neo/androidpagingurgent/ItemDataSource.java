package com.neo.androidpagingurgent;


import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.neo.androidpagingurgent.network.ApiService;
import com.neo.androidpagingurgent.network.RetrofitInstance;
import com.neo.androidpagingurgent.network.model.StackApiResponse;
import com.neo.androidpagingurgent.network.model.StackApiResponse.Item;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
1. DataSource
 */
public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {

    private static final String TAG = "ItemDataSource";
    // all used in making req to api, since it has query for page and page size
    public static final int PAGE_SIZE = 50;
    private static final int FIRST_PAGE = 1;
    private static final String SITE_NAME = "stackoverflow";



    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Item> callback) {
        // first page to be shown(initial)
        ApiService service = RetrofitInstance.getInstance().create(ApiService.class);
        Call<StackApiResponse> call = service.getAnswers(FIRST_PAGE, PAGE_SIZE, SITE_NAME);
        call.enqueue(new Callback<StackApiResponse>() {
            @Override
            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                if(response.isSuccessful()){
                    // passes result to the
                    callback.onResult(response.body().items, null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<StackApiResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {
        // when scroll up gets previous page

        ApiService service = RetrofitInstance.getInstance().create(ApiService.class);
        Call<StackApiResponse> call = service.getAnswers(params.key, PAGE_SIZE, SITE_NAME);
        call.enqueue(new Callback<StackApiResponse>() {
            @Override
            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                if(response.isSuccessful()){
                    if(response.isSuccessful()){
                        // if params.key is > 1 then for sure there's a previous page and decrement page number(key) by 1 to go to previous page
                        Integer key = (params.key > 1) ? params.key - 1 : null;
                        callback.onResult(response.body().items, key);
                    }
                }
            }

            @Override
            public void onFailure(Call<StackApiResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {
        // called when we scroll down to get more data in next page
        ApiService service = RetrofitInstance.getInstance().create(ApiService.class);
        // params.key is retrieved from nextPageKey value passed in LoadInitial
        Call<StackApiResponse> call = service.getAnswers(params.key, PAGE_SIZE, SITE_NAME);

        call.enqueue(new Callback<StackApiResponse>() {
            @Override
            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                if(response.isSuccessful()){
                    Integer key = response.body().has_more ? params.key + 1: null;  // since api has object to tell if we gat more page to load
                    callback.onResult(response.body().items, key);
                }

            }

            @Override
            public void onFailure(Call<StackApiResponse> call, Throwable t) {

            }
        });
    }
}
