package com.ely.rssreader.displayInBrowser;

import com.ely.rssreader.displayFeeds.DisplayFeedsView;

/**
 * Created by lior on 5/18/18.
 */

public class DisplayInBrowserPresenterImpl implements DisplayInBrowserPresenter {
    DisplayInBrowserView view;
    @Override
    public void setView(DisplayInBrowserView view) {
        this.view = view;
    }
}
