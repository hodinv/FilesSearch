package com.hodinv.filessearch.screens.search.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hodinv.filessearch.R;
import com.hodinv.filessearch.databinding.ListitemFilesinfoBinding;
import com.hodinv.filessearch.model.FileInfo;
import com.hodinv.filessearch.screens.search.SearchViewModel;

import java.util.Collections;
import java.util.List;

public class FilesAdapter extends RecyclerView.Adapter<FilesInfoViewHolder> {
    private final SearchViewModel viewModel;

    private List<FileInfo> list = Collections.emptyList();

    public FilesAdapter(SearchViewModel viewModel) {
        this.viewModel = viewModel;
    }

    void setList(List<FileInfo> newList) {
        list = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilesInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListitemFilesinfoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.listitem_filesinfo,
                parent, false);
        binding.setViewModel(viewModel);
        return new FilesInfoViewHolder(binding, binding.getRoot());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull FilesInfoViewHolder holder, int position) {
        holder.bind(list.get(position));
    }
}
