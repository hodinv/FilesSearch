package com.hodinv.filessearch;

import android.content.Context;

import com.hodinv.filessearch.SingleApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @SingleApplicationScope
    @Provides
    public Context context() {
        return context;
    }
}
