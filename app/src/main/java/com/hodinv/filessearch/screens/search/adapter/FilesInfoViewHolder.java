package com.hodinv.filessearch.screens.search.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hodinv.filessearch.databinding.ListitemFilesinfoBinding;
import com.hodinv.filessearch.model.FileInfo;

public class FilesInfoViewHolder extends RecyclerView.ViewHolder {
    private final ListitemFilesinfoBinding binding;

    public FilesInfoViewHolder(@NonNull ListitemFilesinfoBinding binding, @NonNull View itemView) {
        super(itemView);
        this.binding = binding;
    }

    public void bind(FileInfo info) {
        binding.setItem(info);
        binding.executePendingBindings();
    }
}
