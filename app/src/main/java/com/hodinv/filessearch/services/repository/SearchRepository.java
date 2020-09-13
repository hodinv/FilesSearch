package com.hodinv.filessearch.services.repository;


import com.hodinv.filessearch.model.SearchInfo;

import io.reactivex.Observable;

public interface SearchRepository {
    void setSearchValue(String search);

    Observable<String> getSearchValue();

    void postSearchInfo(SearchInfo newInfo);

    Observable<SearchInfo> getSearchInfo();

}
