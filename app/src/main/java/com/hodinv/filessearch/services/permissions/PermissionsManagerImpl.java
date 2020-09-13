package com.hodinv.filessearch.services.permissions;

import android.content.Context;

import com.jakewharton.rxrelay2.BehaviorRelay;

import java.io.File;

import io.reactivex.Observable;


public class PermissionsManagerImpl implements PermissionsManager {
    private final Context context;
    private final BehaviorRelay<Boolean> state = BehaviorRelay.createDefault(false);

    public PermissionsManagerImpl(Context context) {
        this.context = context;
    }

    @Override
    public void request() {
        state.accept(true);
    }

    @Override
    public Observable<Boolean> areAllPermissionsReady() {
        return state;
    }

    @Override
    public File topAccesibleFile() {
        return new File("/");
    }
}
