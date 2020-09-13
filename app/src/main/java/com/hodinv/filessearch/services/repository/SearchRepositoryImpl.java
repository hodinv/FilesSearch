package com.hodinv.filessearch.services.repository;


import androidx.databinding.ObservableField;

import com.hodinv.filessearch.model.SearchInfo;
import com.jakewharton.rxrelay2.BehaviorRelay;

import io.reactivex.Observable;

public class SearchRepositoryImpl implements SearchRepository {

    private BehaviorRelay<String> searchValue = BehaviorRelay.createDefault("");
    private BehaviorRelay<SearchInfo> searchInfo = BehaviorRelay.create();


    @Override
    public void setSearchValue(String search) {
        if (search != null) {
            searchValue.accept(search);
        } else {
            searchValue.accept("");
        }
    }

    @Override
    public Observable<String> getSearchValue() {
        return searchValue;
    }

    @Override
    public void postSearchInfo(SearchInfo newInfo) {
        if (newInfo != null) {
            searchInfo.accept(newInfo);
        }
    }

    @Override
    public Observable<SearchInfo> getSearchInfo() {
        return searchInfo;
    }
}
