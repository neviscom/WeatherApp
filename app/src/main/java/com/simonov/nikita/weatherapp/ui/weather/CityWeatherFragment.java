package com.simonov.nikita.weatherapp.ui.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.simonov.nikita.weatherapp.R;
import com.simonov.nikita.weatherapp.model.CityWeather;
import com.simonov.nikita.weatherapp.model.OneDayForecast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Nikita Simonov
 */

public class CityWeatherFragment extends Fragment implements CityWeatherView {

    @BindView(R.id.current_temp) TextView mCurrentTemp;
    @BindView(R.id.max_temp) TextView mMaxTemp;
    @BindView(R.id.min_temp) TextView mMinTemp;
    @BindView(R.id.main) TextView mMain;
    @BindView(R.id.rv_forecast) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.progress) ProgressBar mProgressBar;

    public static final String KEY_CITY_WEATHER = "cityWeather";

    private CityWeatherPresenter mPresenter;
    private Unbinder mUnbinder;
    private ForecastAdapter mForecastAdapter;

    public static CityWeatherFragment newInstance(@NonNull CityWeather cityWeather) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_CITY_WEATHER, cityWeather);
        CityWeatherFragment fragment = new CityWeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_city_weather, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new CityWeatherPresenter();
        mForecastAdapter = new ForecastAdapter();

        mPresenter.setView(this);

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        CityWeather cityWeather = (CityWeather) getArguments().getSerializable(KEY_CITY_WEATHER);
        if (cityWeather != null) {
            mPresenter.setCityWeather(cityWeather);
        } else {
            throw new IllegalStateException("CityWeather must be passed to CityWeatherFragment");
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mForecastAdapter);

        mPresenter.dispatchCreate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        mPresenter.dispatchDestroy();
    }

    @Override
    public void showCurrentWeather(@NonNull CityWeather cityWeather) {
        mCurrentTemp.setText(cityWeather.getMain().getRoundedTemp());
        mMaxTemp.setText(getString(R.string.temp_max_celsius_degree, cityWeather.getMain().getRoundedTempMax()));
        mMinTemp.setText(getString(R.string.temp_min_celsius_degree, cityWeather.getMain().getRoundedTempMin()));
        if (!cityWeather.getWeatherList().isEmpty()) {
            mMain.setText(cityWeather.getWeatherList().get(0).getMain());
        }
        getActivity().setTitle(cityWeather.getCityName());
    }

    @Override
    public void showForecast(@NonNull List<OneDayForecast> forecastList) {
        mForecastAdapter.setForecastList(forecastList);
    }

    @Override
    public void showLoadingIndicator() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mProgressBar.setVisibility(View.GONE);
    }
}
