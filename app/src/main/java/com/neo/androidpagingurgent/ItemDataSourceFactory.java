package com.neo.androidpagingurgent;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.neo.androidpagingurgent.network.model.StackApiResponse;
import com.neo.androidpagingurgent.network.model.StackApiResponse.Item;

/*
Step 1
 */
public class ItemDataSourceFactory extends DataSource.Factory {
    private MutableLiveData<PageKeyedDataSource<Integer, Item>> itemLiveDataSource = new MutableLiveData<>();


    @NonNull
    @Override
    public DataSource create() {
        // fun returns to us our dataSource
        ItemDataSource itemDataSource = new ItemDataSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Item>> getItemLiveDataSource() {
        // ret the live data PagedKeyed DataSource obj
        return itemLiveDataSource;
    }
}
