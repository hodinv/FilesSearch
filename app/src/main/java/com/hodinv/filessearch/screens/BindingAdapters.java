package com.hodinv.filessearch.screens;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BindingAdapters {

    @BindingAdapter("app:visible")
    public static void setVisible(View view, Boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter(value = {"app:adapter", "app:items"}, requireAll = true)
    public static void setAdapterAndItems(RecyclerView list, ListAdapter adapter, List items) {
        if (list.getAdapter() != adapter) {
            list.setAdapter(adapter);
        }
        adapter.submitList(items);
    }
}
