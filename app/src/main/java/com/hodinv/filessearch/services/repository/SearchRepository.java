package com.hodinv.filessearch.services.repository;

import android.database.Observable;

import com.hodinv.filessearch.model.SearchInfo;

public interface SearchRepository {
    void setSearchValue(String search);

    Observable<String> getSearchValue();

    void postSearchInfo(SearchInfo newInfo);

    Observable<SearchInfo> getSearchInfo();

}
