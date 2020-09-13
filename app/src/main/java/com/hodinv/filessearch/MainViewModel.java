package com.hodinv.filessearch;

import androidx.lifecycle.LiveData;

import com.hodinv.filessearch.mvvm.SingleLiveEvent;
import com.hodinv.filessearch.screens.Screen;
import com.hodinv.filessearch.screens.access.AccessRouter;
import com.hodinv.filessearch.screens.search.SearchRouter;

import javax.inject.Inject;

public class MainViewModel implements AccessRouter, SearchRouter {

    private SingleLiveEvent<Screen> postNextScreen = new SingleLiveEvent<>();
    public LiveData<Screen> nextScreen = postNextScreen;

    @Override
    public void showSearch() {
        postNextScreen.setValue(new Screen.SearchScreen());
    }


}
