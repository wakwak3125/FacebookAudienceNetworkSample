package com.wakwa.facebookaudiencenetworksample;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.ads.AdError;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdsManager;

/**
 * Created by ryo on 2016/12/19.
 */

@SuppressWarnings({"FieldCanBeLocal", "WeakerAccess"})
public class FeedNativeAd implements NativeAdsManager.Listener {
    private static final String TAG = FeedNativeAd.class.getSimpleName();

    private Context context;
    private NativeAdsManager manager;
    private AdLoadingListener adLoadingListener;

    public static final String FEED_AD_ID = "YOUR_AD_ID";

    public FeedNativeAd(final Context context, final AdLoadingListener adLoadingListener) {
        this.context = context;
        manager = new NativeAdsManager(this.context, FEED_AD_ID, 10);
        manager.setListener(this);
        this.adLoadingListener = adLoadingListener;
    }

    @Override
    public void onAdsLoaded() {
        Log.d(TAG, "onAdsLoaded");
        adLoadingListener.onFinishToLoadAds();
    }

    @Override
    public void onAdError(AdError adError) {
        Log.w(TAG, "onAdError: " + adError.getErrorMessage());
        adLoadingListener.onErrorToLoadAd();
    }

    public void loadAds() {
        manager.loadAds();
    }

    @Nullable
    public NativeAd getAd() {
        if (manager != null && manager.isLoaded()) {
            return manager.nextNativeAd();
        } else {
            return null;
        }
    }

    public interface AdLoadingListener {
        void onFinishToLoadAds();
        void onErrorToLoadAd();
    }
}
