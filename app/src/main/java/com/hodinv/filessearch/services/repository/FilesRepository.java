package com.hodinv.filessearch.services.repository;

import com.hodinv.filessearch.model.FileInfo;
import com.hodinv.filessearch.model.FileSort;

import java.util.List;

import io.reactivex.Observable;

public interface FilesRepository {
    void postFiles(List<FileInfo> newFilesList);

    Observable<List<FileInfo>> getFiles();

    Observable<List<FileInfo>> getSortedFiles(FileSort sortType, boolean orderAscending);
}
