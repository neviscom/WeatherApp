package com.simonov.nikita.weatherapp.ui.weather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simonov.nikita.weatherapp.R;
import com.simonov.nikita.weatherapp.model.OneDayForecast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Simonov
 */

class ForecastAdapter extends RecyclerView.Adapter<ForecastViewHolder> {

    @NonNull
    private final List<OneDayForecast> mForecastList = new ArrayList<>();

    void setForecastList(@NonNull List<OneDayForecast> forecastList) {
        mForecastList.clear();
        mForecastList.addAll(forecastList);
        notifyDataSetChanged();
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.li_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        holder.bind(mForecastList.get(position));
    }

    @Override
    public int getItemCount() {
        return mForecastList.size();
    }
}
