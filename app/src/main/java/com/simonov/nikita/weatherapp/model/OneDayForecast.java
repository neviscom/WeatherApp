package com.simonov.nikita.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Nikita Simonov
 */

public class OneDayForecast implements Serializable {

    /**
     * {
     * "dt":1406106000,
     * "main":{
     *      "temp":298.77,
     *      "temp_min":298.77,
     *      "temp_max":298.774,
     *      "pressure":1005.93,
     *      "sea_level":1018.18,
     *      "grnd_level":1005.93,
     *      "humidity":87
     *      "temp_kf":0.26
     *      },
     *  "weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}],
     *  "clouds":{"all":88},
     *  "wind":{"speed":5.71,"deg":229.501},
     *  "sys":{"pod":"d"},
     *  "dt_txt":"2014-07-23 09:00:00"}
     */

    @SerializedName("dt")
    private long mDate;

    @SerializedName("dt_txt")
    private String mDateText;

    @SerializedName("main")
    private Main mMain;

    public long getDate() {
        return mDate;
    }

    public Main getMain() {
        return mMain;
    }

    public String getDateText() {
        return mDateText;
    }
}
