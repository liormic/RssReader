package com.ely.rssreader.displayFeeds;

import com.ely.rssreader.RssItem;

import java.util.List;


public interface DisplayFeedsView {

    void getRssItems(List<RssItem> rssItemList);
    void showNetworkFailureToast();

}
