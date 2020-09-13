package com.hodinv.filessearch;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

public class ApplicationFileSearch extends Application {


    private MainComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerMainComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }


    public static MainComponent get(Activity activity) {
        return ((ApplicationFileSearch) activity.getApplication()).component;
    }

    public static MainComponent get(Service service) {
        return ((ApplicationFileSearch) service.getApplication()).component;
    }

}
