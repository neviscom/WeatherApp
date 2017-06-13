package com.simonov.nikita.weatherapp.ui.weather;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.simonov.nikita.weatherapp.R;
import com.simonov.nikita.weatherapp.model.OneDayForecast;
import com.simonov.nikita.weatherapp.utils.DateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Nikita Simonov
 */

class ForecastViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.forecast_date) TextView mDate;
    @BindView(R.id.forecast_temp) TextView mTemp;

    ForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bind(@NonNull OneDayForecast forecast) {
        Resources resources = itemView.getContext().getResources();
        mDate.setText(DateUtil.formatDate(forecast.getDate()));
        mTemp.setText(resources.getString(R.string.temp_celsius_degree, forecast.getMain().getRoundedTemp()));
    }
}
