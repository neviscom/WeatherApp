package com.simonov.nikita.weatherapp.ui.weather;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.simonov.nikita.weatherapp.R;
import com.simonov.nikita.weatherapp.model.CityWeather;

public class CityWeatherActivity extends AppCompatActivity {

    public static final String EXTRA_CITY_WEATHER = "cityWeather";

    public static void start(@NonNull Context context, @NonNull CityWeather cityWeather) {
        Intent starter = new Intent(context, CityWeatherActivity.class);
        starter.putExtra(EXTRA_CITY_WEATHER, cityWeather);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);

        CityWeather cityWeather = (CityWeather) getIntent().getSerializableExtra(EXTRA_CITY_WEATHER);
        if (cityWeather == null) {
            finish();
            return;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contaier, CityWeatherFragment.newInstance(cityWeather))
                .commit();
    }
}
