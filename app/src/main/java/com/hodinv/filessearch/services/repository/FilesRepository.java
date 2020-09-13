package com.hodinv.filessearch.services.repository;

import com.hodinv.filessearch.model.FileInfo;
import com.hodinv.filessearch.model.FileSort;

import java.util.List;

import io.reactivex.Observable;

public interface FilesRepository {

    void clearAll();


    // to set full list of files (when ready
    void setList(List<FileInfo> filesInfoList);

    Observable<List<FileInfo>> getFiles();

    // to track preparation progress
    void setProgress(int count);

    Observable<Integer> getProgress();


    // return only if there are ready list
    Observable<List<FileInfo>> getSortedFiles(Observable<FileSort> sortType);
}
