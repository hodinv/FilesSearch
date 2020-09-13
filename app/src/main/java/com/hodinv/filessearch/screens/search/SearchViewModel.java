package com.hodinv.filessearch.screens.search;

import androidx.databinding.ObservableField;

import com.hodinv.filessearch.R;
import com.hodinv.filessearch.interactors.files.FilesInteractor;
import com.hodinv.filessearch.interactors.service.ServiceInteractor;
import com.hodinv.filessearch.model.FileInfo;
import com.hodinv.filessearch.model.FileSort;
import com.hodinv.filessearch.mvvm.BaseViewModel;
import com.hodinv.filessearch.mvvm.RxBinding;
import com.hodinv.filessearch.services.permissions.PermissionsManager;
import com.hodinv.filessearch.services.repository.FilesRepository;
import com.hodinv.filessearch.services.repository.SearchRepository;
import com.hodinv.filessearch.services.toasts.ToastsService;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.SerialDisposable;

public class SearchViewModel extends BaseViewModel<SearchRouter> {

    public ObservableField<String> searchValue = new ObservableField<>("");
    public ObservableField<Boolean> pending = new ObservableField<>(true);
    public ObservableField<String> info = new ObservableField<>("");
    public ObservableField<List<FileInfo>> list = new ObservableField<>(Collections.emptyList());

    private ServiceInteractor serviceController;
    private ToastsService toastsService;
    private FilesInteractor filesInteractor;
    private BehaviorRelay<FileSort> sortType = BehaviorRelay.createDefault(FileSort.NONE);
    private PermissionsManager permissionsManager;

    private SerialDisposable writePermissionGet = new SerialDisposable();

    @Inject
    public SearchViewModel(
            SearchRouter router,
            ServiceInteractor serviceController,
            SearchRepository searchRepository,
            FilesRepository filesRepository,
            PermissionsManager permissionsManager,
            FilesInteractor filesInteractor,
            ToastsService toastsService) {
        super(router);
        this.permissionsManager = permissionsManager;
        this.serviceController = serviceController;
        this.filesInteractor = filesInteractor;
        this.toastsService = toastsService;
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
        addDisposable(writePermissionGet);
    }

    void onBack() {
        serviceController.stop();
    }

    void sort(FileSort fileSort) {
        sortType.accept(fileSort);
    }

    void save() {
        writePermissionGet.set(permissionsManager.isWritePermissionGranted().firstOrError().flatMapCompletable(granted -> {
            if (granted) {
                return filesInteractor.saveToDisk(getDestination()).andThen(toastsService.postToast(R.string.toast_saved));
            } else {
                permissionsManager.checkWrite();
                return permissionsManager.isWritePermissionGranted().filter(granter -> granted).firstOrError().flatMapCompletable(dummy -> filesInteractor.saveToDisk(getDestination()).andThen(toastsService.postToast(R.string.toast_saved)));
            }
        }).subscribe());
    }

    public void showDetail(FileInfo fileInfo) {
        router.showDetail(fileInfo);
    }

    private File getDestination() {
        return new File(permissionsManager.topAccesibleFile().getPath() + File.separator + "files_list.txt");
    }

}
