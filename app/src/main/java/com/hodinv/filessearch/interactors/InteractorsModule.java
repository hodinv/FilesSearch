package com.hodinv.filessearch.interactors;

import android.content.Context;

import com.hodinv.filessearch.SingleApplicationScope;
import com.hodinv.filessearch.interactors.files.FilesInteractor;
import com.hodinv.filessearch.interactors.files.FilesInteractorImpl;
import com.hodinv.filessearch.interactors.files.SearchInteractor;
import com.hodinv.filessearch.interactors.files.SearchInteractorImpl;
import com.hodinv.filessearch.interactors.service.ServiceInteractor;
import com.hodinv.filessearch.interactors.service.ServiceInteractorImpl;
import com.hodinv.filessearch.services.repository.FilesRepository;
import com.hodinv.filessearch.services.repository.SearchRepository;


import dagger.Module;
import dagger.Provides;

@Module
public class InteractorsModule {

    @SingleApplicationScope
    @Provides
    FilesInteractor filesInteractor(FilesRepository filesRepository) {
        return new FilesInteractorImpl(filesRepository);
    }

    @SingleApplicationScope
    @Provides
    ServiceInteractor serviceInteractor(Context context) {
        return new ServiceInteractorImpl(context);
    }


    @SingleApplicationScope
    @Provides
    SearchInteractor searchInteractor(SearchRepository searchRepository, FilesRepository filesRepository) {
        return new SearchInteractorImpl(searchRepository, filesRepository);
    }
}
