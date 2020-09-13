package com.hodinv.filessearch.screens.search;

import com.hodinv.filessearch.model.FileInfo;
import com.hodinv.filessearch.mvvm.MvvmRouter;

public interface SearchRouter extends MvvmRouter {
    void showDetail(FileInfo fileInfo);
}
