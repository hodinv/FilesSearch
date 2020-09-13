package com.hodinv.filessearch.interactors.files;


import com.hodinv.filessearch.model.FileInfo;
import com.hodinv.filessearch.model.SearchInfo;
import com.hodinv.filessearch.services.repository.FilesRepository;
import com.hodinv.filessearch.services.repository.SearchRepository;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SearchInteractorImpl implements SearchInteractor {

    private final SearchRepository searchRepository;
    private final FilesRepository filesRepository;

    public SearchInteractorImpl(SearchRepository searchRepository, FilesRepository filesRepository) {
        this.searchRepository = searchRepository;
        this.filesRepository = filesRepository;
    }

    @Override
    public Observable<SearchInfo> search() {
        return Observable.combineLatest(
                filesRepository.getProgress(),
                filesRepository.getFiles(),
                searchRepository.getSearchValue(), (progress, files, search) -> {
                    int count = 0;
                    if (!search.isEmpty() && files.size() > 0) {
                        for (FileInfo file : files) {
                            if (file.fileName.contains(search)) count++;
                        }
                    }
                    return new SearchInfo(search, count, progress);
                }
        ).doOnNext(item -> searchRepository.postSearchInfo(item)).subscribeOn(Schedulers.io());
    }
}
