package com.hodinv.filessearch;

import com.hodinv.filessearch.interactors.ContextModule;
import com.hodinv.filessearch.interactors.FilesInteractor;
import com.hodinv.filessearch.interactors.InteractorsModule;
import com.hodinv.filessearch.screens.RoutingModule;
import com.hodinv.filessearch.screens.ViewModelModule;
import com.hodinv.filessearch.screens.access.AccessFragment;
import com.hodinv.filessearch.screens.access.AccessViewModel;
import com.hodinv.filessearch.screens.search.SearchFragment;
import com.hodinv.filessearch.services.repository.FilesRepository;
import com.hodinv.filessearch.services.ServicesModule;
import com.hodinv.filessearch.services.repository.SearchRepository;

import dagger.Component;

@SingleApplicationScope
@Component(modules = {ServicesModule.class, InteractorsModule.class, ContextModule.class, RoutingModule.class, ViewModelModule.class})
public interface MainComponent {
    FilesRepository getFilesRepository();

    SearchRepository getSearchRepository();

    FilesInteractor getFilesInteractor();

    AccessViewModel getAccessViewModel();

    void injectMainService(MainService service);
    void injectMainActivity(MainActivity activity);
    void injectAccessFragment(AccessFragment accessFragment);
    void injectSearchFragment(SearchFragment searchFragment);
}
