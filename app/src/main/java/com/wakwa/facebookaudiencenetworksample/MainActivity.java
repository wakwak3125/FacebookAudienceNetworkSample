package com.wakwa.facebookaudiencenetworksample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.wakwa.facebookaudiencenetworksample.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FeedNativeAd.AdLoadingListener {

    private ActivityMainBinding binding;
    private FeedNativeAd feedNativeAd;
    private ArrayList<Feed> feedArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setUpAds();
        setUpFeedData();
        // setUpRecyclerView();
    }

    private void setUpFeedData() {
        feedArrayList = Feed.createFeedByCount(20);
    }

    private void setUpAds() {
        feedNativeAd = new FeedNativeAd(getApplicationContext(), this);
        feedNativeAd.loadAds();
    }

    private void setUpRecyclerView() {
        FeedRecyclerAdapter adapter = new FeedRecyclerAdapter(feedArrayList, feedNativeAd);
        binding.feedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.feedRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onFinishToLoadAds() {
        setUpRecyclerView();
    }

    @Override
    public void onErrorToLoadAd() {
        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
    }
}
