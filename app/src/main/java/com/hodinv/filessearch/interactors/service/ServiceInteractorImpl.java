package com.hodinv.filessearch.interactors.service;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.hodinv.filessearch.MainService;
import com.hodinv.filessearch.interactors.service.ServiceInteractor;

public class ServiceInteractorImpl implements ServiceInteractor {

    private final Context context;

    public ServiceInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void start() {
        if (Build.VERSION.SDK_INT > 25) {
            context.startForegroundService(new Intent(context, MainService.class));
        } else {
            context.startService(new Intent(context, MainService.class));
        }
    }

    @Override
    public void stop() {
        context.stopService(new Intent(context, MainService.class));
    }
}
