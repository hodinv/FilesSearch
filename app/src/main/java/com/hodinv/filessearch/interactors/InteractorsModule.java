package com.hodinv.filessearch.interactors;

import com.hodinv.filessearch.SingleApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorsModule {

    @SingleApplicationScope
    @Provides
    FilesInteractor filesInteractor() {
        return new FilesInteractorImpl();
    }
}
