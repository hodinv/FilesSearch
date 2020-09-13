package com.hodinv.filessearch.services.repository;

import com.hodinv.filessearch.model.FileInfo;
import com.hodinv.filessearch.model.FileSort;

import java.util.List;

import io.reactivex.Observable;

public class FilesRepositoryImpl implements FilesRepository {
    @Override
    public void postFiles(List<FileInfo> newFilesList) {

    }

    @Override
    public Observable<List<FileInfo>> getFiles() {
        return null;
    }

    @Override
    public Observable<List<FileInfo>> getSortedFiles(FileSort sortType, boolean orderAscending) {
        return null;
    }
}
