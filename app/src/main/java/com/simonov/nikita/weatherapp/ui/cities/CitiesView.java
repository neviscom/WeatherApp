package com.simonov.nikita.weatherapp.ui.cities;

import android.support.annotation.NonNull;

import com.simonov.nikita.weatherapp.model.CityWeather;
import com.simonov.nikita.weatherapp.ui.LoadingView;

import java.util.List;

/**
 * @author Nikita Simonov
 */

interface CitiesView extends LoadingView {

    void showCities(@NonNull List<CityWeather> cities);

    void addCity(@NonNull CityWeather cityWeather);

    void hideEmptyState();

    void showEmptyState();

    void showAddCityError();
}
