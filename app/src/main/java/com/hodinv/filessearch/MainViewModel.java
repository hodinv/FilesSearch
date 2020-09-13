package com.hodinv.filessearch;

import androidx.lifecycle.LiveData;

import com.hodinv.filessearch.model.FileInfo;
import com.hodinv.filessearch.mvvm.SingleLiveEvent;
import com.hodinv.filessearch.screens.Screen;
import com.hodinv.filessearch.screens.access.AccessRouter;
import com.hodinv.filessearch.screens.detail.DetailRouter;
import com.hodinv.filessearch.screens.search.SearchRouter;

import javax.inject.Inject;

public class MainViewModel implements AccessRouter, SearchRouter, DetailRouter {

    private SingleLiveEvent<Screen> postNextScreen = new SingleLiveEvent<>();
    public LiveData<Screen> nextScreen = postNextScreen;

    @Override
    public void showSearch() {
        postNextScreen.setValue(new Screen.SearchScreen());
    }


    @Override
    public void showDetail(FileInfo fileInfo) {
        postNextScreen.setValue(new Screen.DetailScreen(fileInfo));
    }

}
