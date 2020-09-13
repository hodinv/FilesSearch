package com.hodinv.filessearch.screens.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.hodinv.filessearch.MainComponent;
import com.hodinv.filessearch.R;
import com.hodinv.filessearch.databinding.FragmentSearchBinding;
import com.hodinv.filessearch.model.FileSort;
import com.hodinv.filessearch.mvvm.BackAware;
import com.hodinv.filessearch.mvvm.BaseMvvmFragment;
import com.hodinv.filessearch.screens.search.adapter.FilesAdapter;

public class SearchFragment extends BaseMvvmFragment<SearchViewModel> implements BackAware {
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
        setHasOptionsMenu(true);
        FragmentSearchBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_search,
                container,
                false
        );
        binding.setViewModel(viewModel);
        binding.setAdapter(new FilesAdapter(viewModel));
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_sort, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                viewModel.save();
                return true;
            case R.id.menu_sort_none:
                viewModel.sort(FileSort.NONE);
                return true;
            case R.id.menu_sort_name:
                viewModel.sort(FileSort.NAME);
                return true;
            case R.id.menu_sort_modified:
                viewModel.sort(FileSort.MODIFIED);
                return true;
            case R.id.menu_sort_ext:
                viewModel.sort(FileSort.EXT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onBack() {
        viewModel.onBack();
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.app_name);
    }
}
