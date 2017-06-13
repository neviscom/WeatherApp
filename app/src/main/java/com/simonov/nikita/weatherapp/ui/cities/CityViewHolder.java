package com.simonov.nikita.weatherapp.ui.cities;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.simonov.nikita.weatherapp.R;
import com.simonov.nikita.weatherapp.model.CityWeather;

/**
 * @author Nikita Simonov
 */

class CityViewHolder extends RecyclerView.ViewHolder {

    private TextView mCityName;
    private TextView mDegree;
    private CityClickListener mCityClickListener;

    CityViewHolder(@NonNull View itemView, @Nullable CityClickListener cityClickListener) {
        super(itemView);
        mCityName = (TextView) itemView.findViewById(R.id.city_name);
        mDegree = (TextView) itemView.findViewById(R.id.degree);
        mCityClickListener = cityClickListener;
    }

    void bind(@NonNull CityWeather cityWeather) {
        final Resources resources = itemView.getContext().getResources();
        mCityName.setText(cityWeather.getCityName());
        mDegree.setText(resources.getString(R.string.temp_celsius_degree, cityWeather.getMain().getRoundedTemp()));
        itemView.setOnClickListener(v -> {
            if (mCityClickListener != null) {
                mCityClickListener.onCityClick(cityWeather);
            }
        });
    }

    interface CityClickListener {
        void onCityClick(@NonNull CityWeather cityWeather);
    }
}
