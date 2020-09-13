package com.hodinv.filessearch.screens.search;

import com.hodinv.filessearch.mvvm.BaseViewModel;

import javax.inject.Inject;

public class SearchViewModel extends BaseViewModel<SearchRouter> {

    @Inject
    public SearchViewModel(SearchRouter router) {
        super(router);
    }

}
