package com.ely.rssreader.displayFeeds;

import android.support.annotation.NonNull;

import com.ely.rssreader.RssFeedWrapper;
import com.ely.rssreader.network.CallInterceptor;
import com.ely.rssreader.network.Module;
import com.ely.rssreader.network.RssFeederService;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DisplayFeedsPresenterImpl implements DisplayFeedsPresenter {
    private DisplayFeedsView view;

    @Override
    public void setView(DisplayFeedsView view) {
        this.view = view;
    }

    @Override
    public void executeCall() {
        Call<RssFeedWrapper> feedCallResult = setupRetrofitClient().getRssItemsList();
        feedCallResult.enqueue(new Callback<RssFeedWrapper>() {

            @Override
            public void onResponse(@NonNull Call<RssFeedWrapper> call, @NonNull Response<RssFeedWrapper> response) {
                RssFeedWrapper rssItemList = response.body();
                if (rssItemList != null) {
                    view.getRssItems(rssItemList.getRssItemsList());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RssFeedWrapper> call, @NonNull Throwable t) {
                view.showNetworkFailureToast();
            }
        });

    }

    @Override
    public RssFeederService setupRetrofitClient() {
        Module module = new Module();
        OkHttpClient okHttpClient = module.generateOkHttpClient(new CallInterceptor());
        Retrofit retrofit = module.createRetrofitInstance(okHttpClient);
        return retrofit.create(RssFeederService.class);
    }
}
