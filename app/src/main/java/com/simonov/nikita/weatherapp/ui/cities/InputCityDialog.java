package com.simonov.nikita.weatherapp.ui.cities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.simonov.nikita.weatherapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Nikita Simonov
 */

public class InputCityDialog extends AppCompatDialogFragment {

    @BindView(R.id.et_city_name)
    EditText mEditText;

    private CityInputListener mListener;
    private Unbinder mUnbinder;

    public static InputCityDialog newInstance() {
        return new InputCityDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), getTheme());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.fr_dialog_input, null);
        mUnbinder = ButterKnife.bind(this, view);

        builder.setView(view)
                .setTitle(R.string.input_dialog_title)
                .setPositiveButton(R.string.dialog_ok, (dialog, which) -> onPositiveClick())
                .setNegativeButton(R.string.dialog_cancel, (dialog, which) -> onNegativeClick());
        Dialog dialog = builder.create();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof CityInputListener) {
            mListener = (CityInputListener) context;
        } else {
            throw new IllegalStateException(context.getClass().getSimpleName()
                    + " Activity must implement " + CityInputListener.class.getName());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void onPositiveClick() {
        mListener.onInputed(mEditText.getText().toString());
    }

    private void onNegativeClick() {
        dismiss();
    }

    interface CityInputListener {
        void onInputed(@NonNull String city);
    }
}
