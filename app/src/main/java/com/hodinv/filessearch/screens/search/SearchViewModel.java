package com.hodinv.filessearch.screens.search;

import androidx.databinding.ObservableField;

import com.hodinv.filessearch.interactors.service.ServiceInteractor;
import com.hodinv.filessearch.model.FileInfo;
import com.hodinv.filessearch.model.FileSort;
import com.hodinv.filessearch.mvvm.BaseViewModel;
import com.hodinv.filessearch.mvvm.RxBinding;
import com.hodinv.filessearch.services.repository.FilesRepository;
import com.hodinv.filessearch.services.repository.SearchRepository;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.PublishRelay;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class SearchViewModel extends BaseViewModel<SearchRouter> {

    public ObservableField<String> searchValue = new ObservableField<>("");
    public ObservableField<Boolean> pending = new ObservableField<>(true);
    public ObservableField<String> info = new ObservableField<>("");
    public ObservableField<List<FileInfo>> list = new ObservableField<>(Collections.emptyList());

    private ServiceInteractor serviceController;
    private BehaviorRelay<FileSort> sortType = BehaviorRelay.createDefault(FileSort.NONE);

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
        addDisposable(filesRepository.getSortedFiles(sortType).observeOn(AndroidSchedulers.mainThread()).subscribe(newList -> {
            pending.set(false);
            list.set(newList);
        }));
    }

    void onBack() {
        serviceController.stop();
    }

    void sort(FileSort fileSort) {
        sortType.accept(fileSort);
    }

}
