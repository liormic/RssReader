package com.ely.rssreader.displayFeeds;

import com.ely.rssreader.RssItem;

import java.util.List;

/**
 * Created by lior on 5/17/18.
 */

public interface DisplayFeedsView {

    void getRssItems(List<RssItem> rssItemList);
    void showNetworkFailureToast();

}
