package com.hodinv.filessearch.services.permissions;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import androidx.core.content.ContextCompat;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.PublishRelay;

import java.io.File;

import io.reactivex.Observable;


public class PermissionsManagerImpl implements PermissionsManager {
    private final Context context;

    private final BehaviorRelay<Boolean> grantedRead = BehaviorRelay.createDefault(false);
    private final BehaviorRelay<Boolean> grantedWrite = BehaviorRelay.createDefault(false);
    private final PublishRelay<String[]> command = PublishRelay.create();


    public PermissionsManagerImpl(Context context) {
        this.context = context;
    }

    @Override
    public void checkRead() {
        boolean grantedRead = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        this.grantedRead.accept(grantedRead);
        if (!grantedRead) {
            command.accept(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE});
        }
    }

    @Override
    public void checkWrite() {
        boolean grantedWrite = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        this.grantedWrite.accept(grantedWrite);
        if (!grantedWrite) {
            command.accept(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});
        }
    }

    @Override
    public Observable<Boolean> isReadPermissionGranted() {
        return grantedRead;
    }

    @Override
    public Observable<Boolean> isWritePermissionGranted() {
        return grantedWrite;
    }

    @Override
    public Observable<String[]> checkPermissionCommand() {
        return command;
    }

    @Override
    public File topAccesibleFile() {
        return Environment.getExternalStorageDirectory();
    }


    @Override
    public void setGranted(String permission, boolean granted) {
        if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            grantedRead.accept(granted);
        }
        if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            grantedWrite.accept(granted);
        }
    }
}
