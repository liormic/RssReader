package com.ely.rssreader.network;

import com.ely.rssreader.RssFeedWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RssFeederService {

    @GET("ng/photography/photo-of-the-day/")
    Call<RssFeedWrapper> getRssItemsList();
}
