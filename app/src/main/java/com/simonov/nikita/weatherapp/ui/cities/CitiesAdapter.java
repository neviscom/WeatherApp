package com.simonov.nikita.weatherapp.ui.cities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simonov.nikita.weatherapp.R;
import com.simonov.nikita.weatherapp.model.CityWeather;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Simonov
 */

class CitiesAdapter extends RecyclerView.Adapter<CityViewHolder> {

    @NonNull
    private final List<CityWeather> mData = new ArrayList<>();

    @Nullable
    private CityClickListener mCityClickListener;

    CitiesAdapter(@Nullable CityClickListener cityClickListener) {
        mCityClickListener = cityClickListener;
    }

    public void setData(@NonNull List<CityWeather> cities) {
        mData.clear();
        mData.addAll(cities);
        notifyDataSetChanged();
    }

    void addCity(@NonNull CityWeather cityWeather) {
        int startIndex = mData.size();
        if (mData.add(cityWeather)) {
            notifyItemInserted(startIndex);
        }
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.li_city, parent, false);
        return new CityViewHolder(view, this::onCityClick);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private void onCityClick(@NonNull CityWeather cityWeather) {
        if (mCityClickListener != null) {
            mCityClickListener.onCityClick(cityWeather);
        }
    }

    interface CityClickListener {
        void onCityClick(@NonNull CityWeather cityWeather);
    }
}
