package com.hodinv.filessearch.screens.search;

import androidx.databinding.ObservableField;

import com.hodinv.filessearch.interactors.service.ServiceInteractor;
import com.hodinv.filessearch.mvvm.BaseViewModel;
import com.hodinv.filessearch.mvvm.RxBinding;
import com.hodinv.filessearch.services.repository.FilesRepository;
import com.hodinv.filessearch.services.repository.SearchRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class SearchViewModel extends BaseViewModel<SearchRouter> {

    public ObservableField<String> searchValue = new ObservableField<>("");
    public ObservableField<Boolean> pending = new ObservableField<>(true);
    public ObservableField<String> info = new ObservableField<>("");

    private ServiceInteractor serviceController;

    @Inject
    public SearchViewModel(SearchRouter router, ServiceInteractor serviceController, SearchRepository searchRepository, FilesRepository filesRepository) {
        super(router);
        this.serviceController = serviceController;
        serviceController.start();
        addDisposable(RxBinding.toObservable(searchValue).debounce(300, TimeUnit.MILLISECONDS).subscribe(searchRepository::setSearchValue));
        addDisposable(searchRepository.
                getSearchInfo().observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchInfo -> info.set(
                        (searchInfo.search.isEmpty() ? "-" : searchInfo.found) + " / " + searchInfo.total
                )));
        addDisposable(filesRepository.getFiles().observeOn(AndroidSchedulers.mainThread()).subscribe(list -> pending.set(list.isEmpty())));
    }

    void onBack() {
        serviceController.stop();
    }

}
