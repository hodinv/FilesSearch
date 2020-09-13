package com.hodinv.filessearch.mvvm;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

// see https://habr.com/ru/post/337320/
public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModels;

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>,
            Provider<ViewModel>> viewModels) {
        this.viewModels = viewModels;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Provider<ViewModel> viewModelProvider = viewModels.get(modelClass);

        if (viewModelProvider == null) {
            throw new IllegalArgumentException("model class "
                    + modelClass
                    + " not found");
        }

        return (T) viewModelProvider.get();
    }
}