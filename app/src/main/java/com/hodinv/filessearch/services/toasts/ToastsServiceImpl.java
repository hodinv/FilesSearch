package com.hodinv.filessearch.services.toasts;

import android.content.Context;
import android.widget.Toast;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ToastsServiceImpl implements ToastsService {

    private final Context context;

    public ToastsServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public Completable postToast(String value) {
        return Completable.fromAction(() -> Toast.makeText(context, value, Toast.LENGTH_SHORT).show()).subscribeOn(AndroidSchedulers.mainThread());
    }
}
