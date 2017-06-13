package com.simonov.nikita.weatherapp.app;

import android.app.Application;

import com.orhanobut.hawk.Hawk;
import com.simonov.nikita.weatherapp.BuildConfig;
import com.simonov.nikita.weatherapp.net.OkHttpProvider;
import com.simonov.nikita.weatherapp.repository.RepositoryProvider;

/**
 * @author Nikita Simonov
 */

public class AndroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RepositoryProvider.register(OkHttpProvider.provideClient(), BuildConfig.OPEN_WEATHER_MAP_API_ENDPOINT);
        Hawk.init(this).build();
    }
}
