package com.simonov.nikita.weatherapp.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.simonov.nikita.weatherapp.R;


/**
 * @author Nikita Simonov
 */

public class LoadingDialog extends DialogFragment {

    private static final String TAG = LoadingDialog.class.getSimpleName();
    private static final String TEXT_KEY = "text_id";
    private static final String CANCELABLE = "cancelable";

    @NonNull
    public static LoadingDialog create(@StringRes int textId) {
        return create(textId, false);
    }

    @NonNull
    public static LoadingDialog create(@StringRes int textId, boolean cancelable) {
        Bundle bundle = new Bundle();
        bundle.putInt(TEXT_KEY, textId);
        bundle.putBoolean(CANCELABLE, cancelable);
        LoadingDialog dialog = new LoadingDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    public static void cancel(@Nullable FragmentManager manager) {
        if (manager == null) {
            return;
        }
        Fragment fragment = manager.findFragmentByTag(TAG);
        if (fragment != null && fragment instanceof DialogFragment) {
            ((DialogFragment) fragment).dismissAllowingStateLoss();
        }
    }

    public void show(@NonNull FragmentManager manager) {
        if (manager.findFragmentByTag(TAG) != null) {
            return;
        }
        manager.beginTransaction()
                .add(this, TAG)
                .commitAllowingStateLoss();
    }

    public void cancel() {
        dismiss();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String text = getString(getArguments().getInt(TEXT_KEY, R.string.loading));
        Boolean cancelable = getArguments().getBoolean(CANCELABLE);
        Dialog dialog = new MaterialDialog.Builder(getActivity())
                .progress(true, 0)
                .content(text)
                .cancelable(cancelable)
                .canceledOnTouchOutside(cancelable)
                .build();
        dialog.setOnKeyListener((dialog1, keyCode, event) -> cancelable);
        return dialog;
    }
}
