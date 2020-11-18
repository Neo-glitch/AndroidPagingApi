package com.neo.androidpagingurgent;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.neo.androidpagingurgent.network.model.StackApiResponse;
import com.neo.androidpagingurgent.network.model.StackApiResponse.Item;

/*
step 4
 */
public class MainActivityViewModel extends ViewModel {
    LiveData<PagedList<Item>> itemPagedList;    // kinda similar to a normal list to be passed to adapter
    private LiveData<PageKeyedDataSource<Integer, Item>> itemLiveDatSource;

    public MainActivityViewModel(){
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        itemLiveDatSource = itemDataSourceFactory.getItemLiveDataSource();

        // config obj for the pagedList
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(ItemDataSource.PAGE_SIZE)
                .build();


        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();
    }
}
