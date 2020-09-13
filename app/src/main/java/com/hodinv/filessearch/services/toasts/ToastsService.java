package com.hodinv.filessearch.services.toasts;

import io.reactivex.Completable;

public interface ToastsService {
    Completable postToast(String value);
    Completable postToast(int resId);

}
