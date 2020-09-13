package com.hodinv.filessearch.interactors.files;

import java.io.File;

import io.reactivex.Completable;

public interface FilesInteractor {
    Completable getAllFiles(File topDirectory);
}
