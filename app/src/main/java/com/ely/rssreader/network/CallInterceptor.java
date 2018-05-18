package com.ely.rssreader.network;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class CallInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request sourceRequest = chain.request();
        HttpUrl sourceHttpUrl = sourceRequest.url();
        HttpUrl httpUrl = sourceHttpUrl.newBuilder()
                .build();

        Request request = sourceRequest.newBuilder().url(httpUrl).build();
        return chain.proceed(request);
    }
}
