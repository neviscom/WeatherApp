package com.simonov.nikita.weatherapp.ui.weather;

import android.support.annotation.NonNull;

import com.simonov.nikita.weatherapp.model.CityWeather;
import com.simonov.nikita.weatherapp.model.OneDayForecast;
import com.simonov.nikita.weatherapp.ui.LoadingView;

import java.util.List;

/**
 * @author Nikita Simonov
 */

interface CityWeatherView extends LoadingView {

    void showCurrentWeather(@NonNull CityWeather cityWeather);

    void showForecast(@NonNull List<OneDayForecast> forecastList);
}
