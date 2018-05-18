package com.ely.rssreader.displayFeeds;

import com.ely.rssreader.network.RssFeederService;

public interface DisplayFeedsPresenter {
    void executeCall();
    RssFeederService setupRetrofitClient();
    void setView(DisplayFeedsView view);
}
