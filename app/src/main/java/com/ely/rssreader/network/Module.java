package com.ely.rssreader.network;

import com.ely.rssreader.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Module {

        private static final int TIMEOUTCONNECTINSEC = 30000;
    
        public Retrofit createRetrofitInstance(OkHttpClient okHttpClient) {
            return new Retrofit
                        .Builder()
                        .baseUrl(BuildConfig.BASE_URL_FEEDER)
                        .addConverterFactory(SimpleXmlConverterFactory.create())
                        .client(okHttpClient)
                        .build();
        }

        public OkHttpClient generateOkHttpClient (CallInterceptor callInterceptor){
            return new OkHttpClient.Builder().connectTimeout(TIMEOUTCONNECTINSEC, TimeUnit.SECONDS)
                    .addInterceptor(callInterceptor)
                    .build();
        }
    }

