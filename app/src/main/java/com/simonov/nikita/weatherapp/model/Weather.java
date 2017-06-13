package com.simonov.nikita.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Nikita Simonov
 */

public class Weather implements Serializable {

    /**
     * {"id":501,"main":"Rain","description":"moderate rain","icon":"10n"}
     */

    @SerializedName("id")
    private String mId;

    @SerializedName("main")
    private String mMain;

    @SerializedName("description")
    private String mDescription;

    public String getId() {
        return mId;
    }

    public String getMain() {
        return mMain;
    }

    public String getDescription() {
        return mDescription;
    }
}
