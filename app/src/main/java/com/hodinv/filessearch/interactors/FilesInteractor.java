package com.hodinv.filessearch.interactors;

import com.hodinv.filessearch.model.FileInfo;

import java.io.File;

import io.reactivex.Observable;

public interface FilesInteractor {
    Observable<FileInfo> getAllFiles(File topDirectory);
}
