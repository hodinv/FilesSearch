package com.hodinv.filessearch.screens;

import androidx.lifecycle.ViewModel;

import com.hodinv.filessearch.mvvm.ViewModelKey;
import com.hodinv.filessearch.screens.access.AccessViewModel;
import com.hodinv.filessearch.screens.detail.DetailViewModel;
import com.hodinv.filessearch.screens.search.SearchViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AccessViewModel.class)
    abstract ViewModel accessViewModel(AccessViewModel accessViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    abstract ViewModel searchViewModel(SearchViewModel searchViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    abstract ViewModel detailViewModel(DetailViewModel detailViewModel);
}
