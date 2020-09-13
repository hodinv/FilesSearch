package com.hodinv.filessearch.interactors;

import com.hodinv.filessearch.model.FileInfo;

import java.io.File;

import io.reactivex.Observable;

public class FilesInteractorImpl implements FilesInteractor {
    @Override
    public Observable<FileInfo> getAllFiles(File topDirectory) {
        return null;
    }
}
