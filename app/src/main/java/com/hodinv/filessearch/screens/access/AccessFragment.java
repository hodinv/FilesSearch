package com.hodinv.filessearch.screens.access;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.hodinv.filessearch.MainComponent;
import com.hodinv.filessearch.R;
import com.hodinv.filessearch.databinding.FragmentAccessBinding;
import com.hodinv.filessearch.mvvm.BaseMvvmFragment;

public class AccessFragment extends BaseMvvmFragment<AccessViewModel> {

    @Override
    public void inject(MainComponent component) {
        component.injectAccessFragment(this);
    }

    @Override
    protected Class<AccessViewModel> getViewModelClass() {
        return AccessViewModel.class;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAccessBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_access,
                container,
                false
        );
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //viewModel.onAsk();
    }
}
