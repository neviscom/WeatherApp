package com.simonov.nikita.weatherapp.ui;

import android.support.annotation.NonNull;

import rx.Observable;

/**
 * @author Nikita Simonov
 */
public final class RxDecor {

    private RxDecor() {
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> loading(@NonNull LoadingView view) {
        return observable -> observable
                .doOnSubscribe(view::showLoadingIndicator)
                .doOnTerminate(view::hideLoadingIndicator);
    }

}
