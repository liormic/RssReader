package com.ely.rssreader.displayInBrowser;

public class DisplayInBrowserPresenterImpl implements DisplayInBrowserPresenter {
    private DisplayInBrowserView view;
    @Override
    public void setView(DisplayInBrowserView view) {
        this.view = view;
    }
}
