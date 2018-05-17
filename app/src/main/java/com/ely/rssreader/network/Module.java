package com.ely.rssreader.network;

import java.util.concurrent.TimeUnit;
import java.util.logging.XMLFormatter;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by lior on 5/17/18.
 */

public class Module {

        private static final int TIMEOUTCONNECTINSEC = 100;
        private static final String BASE_URL_FEEDER = "http://feeds.nationalgeographic.com/ng/photography/photo-of-the-day?xml";


        public static Retrofit createRetrofitInstance(OkHttpClient okHttpClient) {
            return new Retrofit
                        .Builder()
                        .baseUrl(BASE_URL_FEEDER)
                        .addConverterFactory(SimpleXmlConverterFactory.create())
                        .client(okHttpClient)
                        .build();
        }

        public  static OkHttpClient generateOkHttpClient (CallInterceptor callInterceptor){
            return new OkHttpClient.Builder().connectTimeout(TIMEOUTCONNECTINSEC, TimeUnit.SECONDS)
                    .addInterceptor(callInterceptor)
                    .build();
        }
    }

