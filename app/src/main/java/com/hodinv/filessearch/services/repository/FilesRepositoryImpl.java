package com.hodinv.filessearch.services.repository;

import com.hodinv.filessearch.model.FileInfo;
import com.hodinv.filessearch.model.FileSort;
import com.hodinv.filessearch.services.repository.comparators.ExtComparator;
import com.hodinv.filessearch.services.repository.comparators.ModifiedComparator;
import com.hodinv.filessearch.services.repository.comparators.NameComparator;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class FilesRepositoryImpl implements FilesRepository {


    private ArrayList<FileInfo> innerList = new ArrayList<>();
    private BehaviorRelay<List<FileInfo>> list = BehaviorRelay.create();
    private BehaviorRelay<Integer> progress = BehaviorRelay.create();

    @Override
    public void clearAll() {
        innerList = new ArrayList<>();
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
    public Observable<List<FileInfo>> getSortedFiles(Observable<FileSort> sortType) {
        return Observable.combineLatest(
                list.filter(list -> !list.isEmpty()).take(1), // lock our copy of files - fires only once
                sortType,
                (files, sort) -> {
                    if (sort != FileSort.NONE) {
                        List<FileInfo> newList = new ArrayList(files);
                        Collections.sort(newList, getComparator(sort));
                        return newList;
                    } else {
                        return files;
                    }
                }
        ).subscribeOn(Schedulers.io());
    }

    private Comparator<FileInfo> getComparator(FileSort fileSort) {
        switch (fileSort) {
            case NAME:
                return new NameComparator();
            case EXT:
                return new ExtComparator();
            case MODIFIED:
                return new ModifiedComparator();
        }
        throw new RuntimeException("No comparator");
    }
}
