package com.simonov.nikita.weatherapp.repository;

import android.support.annotation.NonNull;

import com.simonov.nikita.weatherapp.model.City;
import com.simonov.nikita.weatherapp.model.CityWeather;
import com.simonov.nikita.weatherapp.model.Forecast;

import rx.Observable;

/**
 * @author Nikita Simonov
 */

public interface WeatherRepository {

    @NonNull
    Observable<CityWeather> loadCityWeather(@NonNull City city);

    @NonNull
    Observable<Forecast> loadForecast(@NonNull CityWeather cityWeather);
}
