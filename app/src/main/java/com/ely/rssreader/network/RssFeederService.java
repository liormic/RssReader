package com.ely.rssreader.network;

import com.ely.rssreader.RssFeedWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lior on 5/17/18.
 */

public interface RssFeederService {

    @GET("photo-of-the-day")
    Call<RssFeedWrapper> getRssItemsList();
}
