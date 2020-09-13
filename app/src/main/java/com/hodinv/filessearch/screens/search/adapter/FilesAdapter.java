package com.hodinv.filessearch.screens.search.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.hodinv.filessearch.R;
import com.hodinv.filessearch.databinding.ListitemFilesinfoBinding;
import com.hodinv.filessearch.model.FileInfo;

public class FilesAdapter extends ListAdapter<FileInfo, FilesInfoViewHolder> {
    public FilesAdapter() {
        super(new Diff());
    }

    @NonNull
    @Override
    public FilesInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListitemFilesinfoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.listitem_filesinfo,
                parent, false);
        return new FilesInfoViewHolder(binding, binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull FilesInfoViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private static class Diff extends DiffUtil.ItemCallback<FileInfo> {

        @Override
        public boolean areItemsTheSame(@NonNull FileInfo oldItem, @NonNull FileInfo newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull FileInfo oldItem, @NonNull FileInfo newItem) {
            return oldItem.equals(newItem);
        }
    }
}
