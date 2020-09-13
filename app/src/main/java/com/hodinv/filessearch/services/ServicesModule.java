package com.hodinv.filessearch.services;

import android.content.Context;

import com.hodinv.filessearch.SingleApplicationScope;
import com.hodinv.filessearch.ContextModule;
import com.hodinv.filessearch.services.notification.NotificationManager;
import com.hodinv.filessearch.services.notification.NotificationManagerImpl;
import com.hodinv.filessearch.services.permissions.PermissionsManager;
import com.hodinv.filessearch.services.permissions.PermissionsManagerImpl;
import com.hodinv.filessearch.services.repository.FilesRepository;
import com.hodinv.filessearch.services.repository.FilesRepositoryImpl;
import com.hodinv.filessearch.services.repository.SearchRepository;
import com.hodinv.filessearch.services.repository.SearchRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ContextModule.class})
public class ServicesModule {

    @SingleApplicationScope
    @Provides
    public FilesRepository filesRepository() {
        return new FilesRepositoryImpl();
    }

    @SingleApplicationScope
    @Provides
    public SearchRepository searchRepository() {
        return new SearchRepositoryImpl();
    }

    @SingleApplicationScope
    @Provides
    public PermissionsManager permissionsManager(Context context) {
        return new PermissionsManagerImpl(context);
    }

    @SingleApplicationScope
    @Provides
    public NotificationManager notificationManager(Context context) {
        return new NotificationManagerImpl(context);
    }

}
