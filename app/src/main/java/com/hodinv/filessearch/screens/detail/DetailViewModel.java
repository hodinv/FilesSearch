package com.hodinv.filessearch.screens.detail;

import androidx.databinding.ObservableField;

import com.hodinv.filessearch.model.FileInfo;
import com.hodinv.filessearch.mvvm.BaseViewModel;

import javax.inject.Inject;

public class DetailViewModel extends BaseViewModel<DetailRouter> {

    public ObservableField<FileInfo> info = new ObservableField<>();

    @Inject
    public DetailViewModel(DetailRouter router) {
        super(router);
    }

    public void setFileInfo(FileInfo fileInfo) {
        info.set(fileInfo);
    }
}
