package com.simonov.nikita.weatherapp.ui.cities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.simonov.nikita.weatherapp.R;
import com.simonov.nikita.weatherapp.model.CityWeather;
import com.simonov.nikita.weatherapp.ui.LoadingDialog;
import com.simonov.nikita.weatherapp.ui.weather.CityWeatherActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Nikita Simonov
 */

public class CitiesListActivity extends AppCompatActivity implements
        CitiesView, CitiesAdapter.CityClickListener,
        InputCityDialog.CityInputListener {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.fab) FloatingActionButton mFab;
    @BindView(R.id.cities_list_recycler) RecyclerView mRecyclerView;
    @BindView(R.id.tv_empty) TextView mEmptyView;

    private CitiesPresenter mPresenter;
    private CitiesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_list);
        ButterKnife.bind(this);

        mPresenter = new CitiesPresenter();
        mAdapter = new CitiesAdapter(this);

        setSupportActionBar(mToolbar);

        mPresenter.setView(this);
        mFab.setOnClickListener(view ->
                InputCityDialog.newInstance().show(getSupportFragmentManager(), InputCityDialog.class.getName()));

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.dispatchCreate();
    }

    @Override
    public void showCities(@NonNull List<CityWeather> cities) {
        mAdapter.setData(cities);
    }

    @Override
    public void addCity(@NonNull CityWeather cityWeather) {
        mAdapter.addCity(cityWeather);
    }

    @Override
    public void hideEmptyState() {
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyState() {
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAddCityError() {
        Toast.makeText(this, R.string.error_city_loading, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCityClick(@NonNull CityWeather cityWeather) {
        CityWeatherActivity.start(this, cityWeather);
    }

    @Override
    public void onInputed(@NonNull String city) {
        mPresenter.addCity(city);
    }

    @Override
    public void showLoadingIndicator() {
        LoadingDialog.create(R.string.loading).show(getSupportFragmentManager());
    }

    @Override
    public void hideLoadingIndicator() {
        LoadingDialog.cancel(getSupportFragmentManager());
    }
}
