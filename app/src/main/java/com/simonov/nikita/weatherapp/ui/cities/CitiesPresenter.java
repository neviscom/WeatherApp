package com.simonov.nikita.weatherapp.ui.cities;

import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;
import com.simonov.nikita.weatherapp.model.City;
import com.simonov.nikita.weatherapp.model.CityWeather;
import com.simonov.nikita.weatherapp.repository.RepositoryProvider;
import com.simonov.nikita.weatherapp.ui.RxDecor;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author Nikita Simonov
 */

class CitiesPresenter {

    private static final String CITIES = "cities";
    private static final String CITY_WEATHERS = "cityWeathers";

    private CitiesView mView;
    @NonNull
    private List<City> mCityList = new ArrayList<>();
    @NonNull
    private List<CityWeather> mCityWeathers = new ArrayList<>();

    public void setView(@NonNull CitiesView view) {
        mView = view;
    }

    void dispatchCreate() {
        if (!Hawk.contains(CITIES)) {
            mCityList.add(new City("Moscow"));
            mCityList.add(new City("Saint Petersburg"));
        } else {
            mCityList.addAll(Hawk.get(CITIES));
        }
        loadWeather();
    }

    void addCity(@NonNull String city) {
        Observable
                .just(new City(city))
                .flatMap(loadCityWeather())
                .first()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxDecor.loading(mView))
                .subscribe(this::cityLoaded, this::onAddCityError);
    }

    private void loadWeather() {
        Observable
                .from(mCityList)
                .flatMap(loadCityWeather())
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxDecor.loading(mView))
                .subscribe(this::citiesLoaded, this::onLoadingError);
    }

    private void citiesLoaded(@NonNull List<CityWeather> cityWeathers) {
        mCityWeathers.addAll(cityWeathers);
        mView.hideEmptyState();
        mView.showCities(cityWeathers);
        saveWeather();
    }

    private void cityLoaded(@NonNull CityWeather cityWeather) {
        mCityList.add(new City(cityWeather.getCityName()));
        Hawk.put(CITIES, mCityList);
        mCityWeathers.add(cityWeather);
        mView.hideEmptyState();
        mView.addCity(cityWeather);
        saveWeather();
    }

    private void saveWeather() {
        Hawk.put(CITY_WEATHERS, mCityWeathers);
    }

    private void onAddCityError(@NonNull Throwable throwable) {
        mView.showAddCityError();
    }

    private void onLoadingError(@NonNull Throwable throwable) {
        List<CityWeather> cityWeathers = Hawk.get(CITY_WEATHERS);
        mView.hideEmptyState();
        mView.showCities(cityWeathers);
        if (cityWeathers.isEmpty()) {
            mView.showEmptyState();
        }
    }

    private Func1<City, Observable<CityWeather>> loadCityWeather() {
        return city -> RepositoryProvider.provideWeatherRepository().loadCityWeather(city);
    }

}
