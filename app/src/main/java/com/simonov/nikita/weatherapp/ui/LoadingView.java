package com.simonov.nikita.weatherapp.ui;

/**
 * @author Nikita Simonov
 */

/**
 * Interface representing a View that will use to load data.
 */
public interface LoadingView {
    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoadingIndicator();

    /**
     * Hide a loading view.
     */
    void hideLoadingIndicator();

}
