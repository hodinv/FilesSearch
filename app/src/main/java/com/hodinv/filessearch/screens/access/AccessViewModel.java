package com.hodinv.filessearch.screens.access;

import com.hodinv.filessearch.mvvm.BaseViewModel;
import com.hodinv.filessearch.services.permissions.PermissionsManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class AccessViewModel extends BaseViewModel<AccessRouter> {

    private final PermissionsManager permissionsManager;

    @Inject
    public AccessViewModel(AccessRouter router, PermissionsManager permissionsManager) {
        super(router);
        this.permissionsManager = permissionsManager;
        addDisposable(permissionsManager.isReadPermissionGranted().observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result) {
                        router.showSearch();
                    }
                }));
    }

    public void onAsk() {
        permissionsManager.checkRead();
    }

}
