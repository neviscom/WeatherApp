package com.simonov.nikita.weatherapp.net;

import com.simonov.nikita.weatherapp.model.CityWeather;
import com.simonov.nikita.weatherapp.model.Forecast;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Nikita Simonov
 */

public interface WeatherService {

    @GET("weather")
    Observable<CityWeather> loadCityWeather(
            @Query("q") String cityName,
            @Query("units") String units
    );

    @GET("forecast")
    Observable<Forecast> loadForecast(
            @Query("id") long cityId,
            @Query("units") String units
    );
}
