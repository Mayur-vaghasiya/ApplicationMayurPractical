package com.example.application.apploader;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.application.util.Staticdatautility.BASE_URL;


public class ApplicationLoader extends Application {

    public static final String TAG = ApplicationLoader.class.getSimpleName();
    private static ApplicationLoader applicationLoader;

    private Retrofit retrofit;

    public static synchronized ApplicationLoader getAppLoader() {

        return applicationLoader;

    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationLoader = this;

    }

    public Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
