package com.simonov.nikita.weatherapp.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Nikita Simonov
 */

public class City implements Serializable {

    @SerializedName("id")
    private long mId;

    @NonNull
    @SerializedName("name")
    private String mCityName;

    public City(@NonNull String cityName) {
        mCityName = cityName;
    }

    @NonNull
    public String getCityName() {
        return mCityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return mCityName.equals(city.mCityName);

    }

    @Override
    public int hashCode() {
        return mCityName.hashCode();
    }
}
