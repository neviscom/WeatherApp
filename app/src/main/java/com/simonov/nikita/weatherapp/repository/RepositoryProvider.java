package com.simonov.nikita.weatherapp.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.GsonBuilder;
import com.simonov.nikita.weatherapp.net.WeatherService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Nikita Simonov
 */

public class RepositoryProvider {

    private static Retrofit sRetrofit;

    @Nullable
    private static WeatherRepository sWeatherRepository;

    private RepositoryProvider() {
        // not implemented
    }

    public static void register(@NonNull OkHttpClient client, String baseUrl) {
        sRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @NonNull
    public static WeatherRepository provideWeatherRepository() {
        if (sWeatherRepository == null) {
            sWeatherRepository = new WeatherRepositoryImpl(sRetrofit.create(WeatherService.class));
        }
        return sWeatherRepository;
    }
}
