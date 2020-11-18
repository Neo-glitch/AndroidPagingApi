package com.neo.androidpagingurgent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.neo.androidpagingurgent.adapter.ItemAdapter;
import com.neo.androidpagingurgent.network.ApiService;
import com.neo.androidpagingurgent.network.RetrofitInstance;
import com.neo.androidpagingurgent.network.model.StackApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MainActivityViewModel mViewModel;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        ItemAdapter adapter = new ItemAdapter();

        mViewModel.itemPagedList.observe(this, items -> adapter.submitList(items));

        mRecyclerView.setAdapter(adapter);
    }


}