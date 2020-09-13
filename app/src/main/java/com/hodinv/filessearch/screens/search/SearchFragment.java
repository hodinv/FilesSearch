package com.hodinv.filessearch.screens.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.hodinv.filessearch.MainComponent;
import com.hodinv.filessearch.R;
import com.hodinv.filessearch.databinding.FragmentSearchBinding;
import com.hodinv.filessearch.mvvm.BaseMvvmFragment;

public class SearchFragment extends BaseMvvmFragment<SearchViewModel> {
    @Override
    public void inject(MainComponent component) {
        component.injectSearchFragment(this);
    }

    @Override
    protected Class<SearchViewModel> getViewModelClass() {
        return SearchViewModel.class;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentSearchBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_search,
                container,
                false
        );
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }
}
