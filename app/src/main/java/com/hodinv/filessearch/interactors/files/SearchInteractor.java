package com.hodinv.filessearch.interactors.files;


import com.hodinv.filessearch.model.SearchInfo;

import io.reactivex.Observable;

public interface SearchInteractor {
    Observable<SearchInfo> search();
}
