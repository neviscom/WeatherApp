package com.simonov.nikita.weatherapp.net;

import android.support.annotation.NonNull;

import com.simonov.nikita.weatherapp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author Nikita Simonov
 */

public class OkHttpProvider {

    private static final int CONNECTION_TIMEOUT_SEC = 15;
    private static final String APPID = "APPID";

    private OkHttpProvider() {
    }

    public static OkHttpClient provideClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS);
        if (!BuildConfig.BUILD_TYPE.equals("release")) {
            setupDebug(builder);
        }
        builder.addInterceptor(chain -> {
            Request request = chain.request();

            HttpUrl url = request.url().newBuilder()
                    .addQueryParameter(APPID, BuildConfig.OPEN_WEATHER_MAP_API_KEY)
                    .build();

            request = request
                    .newBuilder()
                    .url(url)
                    .build();
            return chain.proceed(request);
        });
        return builder.build();
    }

    private static void setupDebug(@NonNull final OkHttpClient.Builder builder) {
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
    }

}
