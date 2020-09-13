package com.hodinv.filessearch.services.permissions;


import java.io.File;

import io.reactivex.Observable;

public interface PermissionsManager {
    // request permissions or just populate current permissions
    void request();

    Observable<Boolean> areAllPermissionsReady();

    File topAccesibleFile();
}
