package com.hodinv.filessearch.screens;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter("app:visible")
    public static void setVisible(View view, Boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }


}
