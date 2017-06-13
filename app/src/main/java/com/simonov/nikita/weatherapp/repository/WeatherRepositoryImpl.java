package com.simonov.nikita.weatherapp.repository;

import android.support.annotation.NonNull;

import com.simonov.nikita.weatherapp.model.City;
import com.simonov.nikita.weatherapp.model.CityWeather;
import com.simonov.nikita.weatherapp.model.Forecast;
import com.simonov.nikita.weatherapp.net.WeatherService;

import rx.Observable;

/**
 * @author Nikita Simonov
 */

class WeatherRepositoryImpl implements WeatherRepository {

    private static final String METRIC = "metric";

    @NonNull
    private final WeatherService mWeatherService;

    WeatherRepositoryImpl(@NonNull WeatherService weatherService) {
        mWeatherService = weatherService;
    }

    @NonNull
    @Override
    public Observable<CityWeather> loadCityWeather(@NonNull City city) {
        return mWeatherService.loadCityWeather(city.getCityName(), METRIC);
    }

    @NonNull
    @Override
    public Observable<Forecast> loadForecast(@NonNull CityWeather cityWeather) {
        return mWeatherService.loadForecast(cityWeather.getCityId(), METRIC);
    }
}
