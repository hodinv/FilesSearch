package com.hodinv.filessearch.screens.search.adapter;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.hodinv.filessearch.model.FileInfo;

import java.util.List;

public class BindingAdapters {

    @BindingAdapter(value = {"app:pattern", "app:value"}, requireAll = true)
    public static void setVisibileByPAttern(View view, String pattern, String value) {
        if (pattern.isEmpty() || !value.toLowerCase().contains(pattern.toLowerCase())) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    @BindingAdapter(value = {"app:adapter", "app:items"}, requireAll = true)
    public static void setAdapterAndItems(RecyclerView list, FilesAdapter adapter, List<FileInfo> items) {
        if (list.getAdapter() != adapter) {
            list.setAdapter(adapter);
        }
        adapter.setList(items);
    }
}
