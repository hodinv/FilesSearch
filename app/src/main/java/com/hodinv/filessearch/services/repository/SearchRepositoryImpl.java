package com.hodinv.filessearch.services.repository;

import android.database.Observable;

import com.hodinv.filessearch.model.SearchInfo;

public class SearchRepositoryImpl implements SearchRepository {
    @Override
    public void setSearchValue(String search) {

    }

    @Override
    public Observable<String> getSearchValue() {
        return null;
    }

    @Override
    public void postSearchInfo(SearchInfo newInfo) {

    }

    @Override
    public Observable<SearchInfo> getSearchInfo() {
        return null;
    }
}
