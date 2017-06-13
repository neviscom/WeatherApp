package com.simonov.nikita.weatherapp.ui.weather;

import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;
import com.simonov.nikita.weatherapp.model.CityWeather;
import com.simonov.nikita.weatherapp.model.Forecast;
import com.simonov.nikita.weatherapp.model.OneDayForecast;
import com.simonov.nikita.weatherapp.repository.RepositoryProvider;
import com.simonov.nikita.weatherapp.ui.RxDecor;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Nikita Simonov
 */

class CityWeatherPresenter {

    private static final String FORECAST = "forecast_%s";

    private CityWeatherView mView;
    private CityWeather mCityWeather;
    private Subscription mSubscription;

    public void setView(@NonNull CityWeatherView view) {
        mView = view;
    }

    public void setCityWeather(@NonNull CityWeather cityWeather) {
        mCityWeather = cityWeather;
    }

    void dispatchCreate() {
        loadForecast();
        mView.showCurrentWeather(mCityWeather);
    }

    void dispatchDestroy() {
        mSubscription.unsubscribe();
    }

    private void loadForecast() {
        mSubscription = RepositoryProvider.provideWeatherRepository()
                .loadForecast(mCityWeather)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxDecor.loading(mView))
                .subscribe(this::onForecast, this::onError);
    }

    private void onForecast(@NonNull Forecast forecast) {
        List<OneDayForecast> forecastList = forecast.getForecastList();
        mView.showForecast(forecastList);
        Hawk.put(getForecastKey(), forecast);
    }

    private void onError(@NonNull Throwable throwable) {
        String forecastKey = getForecastKey();
        if (Hawk.contains(forecastKey)) {
            Forecast forecast = Hawk.get(forecastKey);
            mView.showForecast(forecast.getForecastList());
        }
    }

    private String getForecastKey(){
        return String.format(FORECAST, mCityWeather.getCityId());
    }
}
