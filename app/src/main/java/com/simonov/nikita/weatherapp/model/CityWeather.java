package com.simonov.nikita.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nikita Simonov
 */

public class CityWeather implements Serializable {

    /**
     * {
     * "coord":{"lon":-0.13,"lat":51.51},
     * "weather":[{"id":300,"main":"Drizzle","description":"light intensity drizzle","icon":"09d"}],
     * "base":"stations",
     * "main":{"temp":280.32,"pressure":1012,"humidity":81,"temp_min":279.15,"temp_max":281.15},
     * "visibility":10000,
     * "wind":{"speed":4.1,"deg":80},
     * "clouds":{"all":90},
     * "dt":1485789600,
     * "sys":{"type":1,"id":5091,"message":0.0103,"country":"GB","sunrise":1485762037,"sunset":1485794875},
     * "id":2643743,
     * "name":"London",
     * "cod":200}
     */

    @SerializedName("weather")
    private List<Weather> mWeatherList;

    @SerializedName("main")
    private Main mMain;

    @SerializedName("name")
    private String mCityName;

    @SerializedName("id")
    private long mCityId;

    public Main getMain() {
        return mMain;
    }

    public String getCityName() {
        return mCityName;
    }

    public long getCityId() {
        return mCityId;
    }

    public List<Weather> getWeatherList() {
        return mWeatherList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityWeather that = (CityWeather) o;

        return mCityId == that.mCityId;

    }

    @Override
    public int hashCode() {
        return (int) (mCityId ^ (mCityId >>> 32));
    }
}
