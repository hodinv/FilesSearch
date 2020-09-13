package com.hodinv.filessearch.mvvm;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.hodinv.filessearch.ApplicationFileSearch;
import com.hodinv.filessearch.MainComponent;

import javax.inject.Inject;

public abstract class BaseMvvmFragment<VM extends BaseViewModel> extends Fragment {

    @Inject
    ViewModelFactory viewModelFactory;

    protected VM viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        inject(ApplicationFileSearch.get(getActivity()));
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass());
    }

    protected abstract void inject(MainComponent component);
    protected abstract Class<VM> getViewModelClass();
}
