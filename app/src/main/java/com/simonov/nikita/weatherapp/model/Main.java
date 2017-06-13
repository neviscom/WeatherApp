package com.simonov.nikita.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Nikita Simonov
 */

public class Main implements Serializable {

    /**
     * "main":{"temp":280.32,"pressure":1012,"humidity":81,"temp_min":279.15,"temp_max":281.15},
     */

    @SerializedName("temp")
    private double mTemp;

    @SerializedName("temp_min")
    private double mTempMin;

    @SerializedName("temp_max")
    private double mTempMax;

    @SerializedName("pressure")
    private String mPressure;

    public double getTemp() {
        return mTemp;
    }

    public String getRoundedTemp() {
        return roundTemp(mTemp);
    }

    public String getRoundedTempMin() {
        return roundTemp(mTempMin);
    }

    public String getRoundedTempMax() {
        return roundTemp(mTempMax);
    }

    private String roundTemp(double temp) {
        return String.valueOf(Math.round(mTemp));
    }

}
