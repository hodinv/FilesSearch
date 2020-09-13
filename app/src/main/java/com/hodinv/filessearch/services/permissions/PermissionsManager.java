package com.hodinv.filessearch.services.permissions;


import java.io.File;

import io.reactivex.Observable;

public interface PermissionsManager {
    // check permissions, request of not all granted
    void checkRead();

    void checkWrite();

    Observable<Boolean> isReadPermissionGranted();

    Observable<Boolean> isWritePermissionGranted();

    Observable<String[]> checkPermissionCommand();

    File topAccesibleFile();

    void setGranted(String permission, boolean granted);
}
