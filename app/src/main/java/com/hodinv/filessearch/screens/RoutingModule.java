package com.hodinv.filessearch.screens;

import com.hodinv.filessearch.MainViewModel;
import com.hodinv.filessearch.SingleApplicationScope;
import com.hodinv.filessearch.screens.access.AccessRouter;
import com.hodinv.filessearch.screens.search.SearchRouter;

import dagger.Module;
import dagger.Provides;

@Module
public class RoutingModule {

    @SingleApplicationScope
    @Provides
    MainViewModel getMainViewModel() {
        return new MainViewModel();
    }

    @Provides
    AccessRouter getAccessRouter(MainViewModel mainViewModel) {
        return mainViewModel;
    }

    @Provides
    SearchRouter getSearchRouter(MainViewModel mainViewModel) {
        return mainViewModel;
    }


}
