package com.hodinv.filessearch;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.hodinv.filessearch.interactors.files.FilesInteractor;
import com.hodinv.filessearch.interactors.files.SearchInteractor;
import com.hodinv.filessearch.services.notification.NotificationManager;
import com.hodinv.filessearch.services.permissions.PermissionsManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainService extends Service {

    @Inject
    FilesInteractor filesInteractor;

    @Inject
    PermissionsManager permissionsManager;

    @Inject
    NotificationManager notificationManager;

    @Inject
    SearchInteractor searchInteractor;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationFileSearch.get(this).injectMainService(this);

        startForeground(notificationManager.getNotificationId(), notificationManager.getNotification());

        disposables.add(permissionsManager.isReadPermissionGranted().subscribeOn(Schedulers.io())
                .distinctUntilChanged()
                .switchMapCompletable(state -> {
                    if (state) {
                        return filesInteractor.getAllFiles(permissionsManager.topAccesibleFile());
                    } else {
                        return Completable.complete();
                    }
                }).subscribe());

        disposables.add(
                searchInteractor.search().throttleLast(1, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(state -> notificationManager.updateNotification(state))
        );
    }

}
