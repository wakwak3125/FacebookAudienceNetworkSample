package com.wakwa.facebookaudiencenetworksample;

import java.util.ArrayList;

/**
 * Created by ryo on 2016/12/19.
 */

public class Feed {

    private static final int AD_POSITION = 10;

    private long id;
    private String title;
    private boolean isAd = false;

    private Feed(long id, String title, boolean isAd) {
        this.id = id;
        this.title = title;
        this.isAd = isAd;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAd() {
        return isAd;
    }

    public static ArrayList<Feed> createFeedByCount(final int count) {
        final ArrayList<Feed> feedArrayList = new ArrayList<>();

        for (int i = 0; i <= count; i++) {
            if (isAdPosition(i)) {
                feedArrayList.add(new Feed(((long) i), "title:" + String.valueOf(i), true));
            } else {
                feedArrayList.add(new Feed(((long) i), "title:" + String.valueOf(i), false));
            }
        }
        return feedArrayList;
    }

    private static boolean isAdPosition(int i) {
        return i % AD_POSITION == 0 && i != 0;
    }
}
