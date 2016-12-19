package com.wakwa.facebookaudiencenetworksample;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.facebook.ads.NativeAd;
import com.wakwa.facebookaudiencenetworksample.databinding.RowAdBinding;
import com.wakwa.facebookaudiencenetworksample.databinding.RowNormalBinding;

import java.util.ArrayList;

/**
 * Created by ryo on 2016/12/19.
 */

public class FeedRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int ARTICLE = 0;
    private static int AD = 1;

    private ArrayList<Feed> feedArrayList;
    private FeedNativeAd feedNativeAd;

    public FeedRecyclerAdapter(ArrayList<Feed> feedArrayList, FeedNativeAd feedNativeAd) {
        this.feedArrayList = feedArrayList;
        this.feedNativeAd = feedNativeAd;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == AD) {
            return new AdViewHolder(RowAdBinding.inflate(LayoutInflater.from(parent.getContext())));
        } else {
            return new FeedViewHolder(RowNormalBinding.inflate(LayoutInflater.from(parent.getContext())));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AdViewHolder) {
            ((AdViewHolder) holder).showAd(feedNativeAd.getAd());
        } else {
            ((FeedViewHolder) holder).showArticle(feedArrayList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return feedArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (feedArrayList.get(position).isAd()) {
            return AD;
        } else {
            return ARTICLE;
        }
    }

    static class AdViewHolder extends RecyclerView.ViewHolder {

        RowAdBinding binding;

        AdViewHolder(RowAdBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public RowAdBinding getAdBinding() {
            return binding;
        }

        void showAd(@Nullable NativeAd ad) {
            if (ad == null) return;
            binding.adTitle.setText(ad.getAdTitle());
            binding.adSponsor.setText(ad.getAdSubtitle());
            binding.adBody.setText(ad.getAdBody());
            binding.adButton.setText(ad.getAdCallToAction());
        }
    }

    static class FeedViewHolder extends RecyclerView.ViewHolder {

        RowNormalBinding binding;

        FeedViewHolder(RowNormalBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public RowNormalBinding getRowNolmalBinding() {
            return binding;
        }

        void showArticle(Feed feed) {
            binding.articleId.setText(String.valueOf(feed.getId()));
            binding.title.setText(feed.getTitle());
        }
    }
}
