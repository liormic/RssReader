package com.ely.rssreader.displayFeeds;

import com.ely.rssreader.network.RssFeederService;

/**
 * Created by lior on 5/17/18.
 */

public interface DisplayFeedsPresenter {
    void executeCall();
    RssFeederService setupRetrofitClient();
    void setView(DisplayFeedsView view);
}
