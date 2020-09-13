package com.hodinv.filessearch.screens.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.hodinv.filessearch.MainComponent;
import com.hodinv.filessearch.R;
import com.hodinv.filessearch.databinding.FragmentDetailBinding;
import com.hodinv.filessearch.model.FileInfo;
import com.hodinv.filessearch.mvvm.BaseMvvmFragment;

public class DetailFragment extends BaseMvvmFragment<DetailViewModel> {
    @Override
    protected void inject(MainComponent component) {
        component.injectDetailFragment(this);
    }

    @Override
    protected Class<DetailViewModel> getViewModelClass() {
        return DetailViewModel.class;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentDetailBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_detail,
                container,
                false
        );
        viewModel.setFileInfo(getArguments().getParcelable(ARG));
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FileInfo info = getArguments().getParcelable(ARG);
        getActivity().setTitle(info.fileName);
    }

    private static String ARG = "fileInfo";

    public static DetailFragment getInstance(FileInfo fileInfo) {
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG, fileInfo);
        fragment.setArguments(bundle);
        return fragment;
    }
}
