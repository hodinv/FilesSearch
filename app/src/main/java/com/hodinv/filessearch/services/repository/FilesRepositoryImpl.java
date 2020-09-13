package com.hodinv.filessearch.services.repository;

import com.hodinv.filessearch.model.FileInfo;
import com.hodinv.filessearch.model.FileSort;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class FilesRepositoryImpl implements FilesRepository {


    private ArrayList<FileInfo> innerList = new ArrayList<>();
    private BehaviorRelay<List<FileInfo>> list = BehaviorRelay.create();
    private BehaviorRelay<Integer> progress = BehaviorRelay.create();

    @Override
    public void clearAll() {
        innerList.clear();
        list.accept(new ArrayList());
        progress.accept(0);
    }

    @Override
    public void setList(List<FileInfo> filesInfoList) {
        list.accept(filesInfoList);
        progress.accept(filesInfoList.size());
    }


    @Override
    public Observable<List<FileInfo>> getFiles() {
        return list;
    }

    @Override
    public void setProgress(int count) {
        progress.accept(count);
    }

    @Override
    public Observable<Integer> getProgress() {
        return progress;
    }

    @Override
    public Observable<List<FileInfo>> getSortedFiles(FileSort sortType, boolean orderAscending) {
        return list;
    }
}
