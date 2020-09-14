package com.hodinv.filessearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.content.pm.PackageManager;
import android.os.Bundle;

import com.hodinv.filessearch.mvvm.BackAware;
import com.hodinv.filessearch.screens.Screen;
import com.hodinv.filessearch.screens.access.AccessFragment;
import com.hodinv.filessearch.screens.detail.DetailFragment;
import com.hodinv.filessearch.screens.search.SearchFragment;
import com.hodinv.filessearch.services.permissions.PermissionsManager;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.SerialDisposable;

public class MainActivity extends AppCompatActivity {


    @Inject
    MainViewModel viewModel;

    @Inject
    PermissionsManager permissionsManager;

    private final SerialDisposable permissionsDisposable = new SerialDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApplicationFileSearch.get(this).injectMainActivity(this);
        viewModel.nextScreen.observe(this, next -> {
            if (next instanceof Screen.SearchScreen) {
                startFragment(new SearchFragment());
            }
            if (next instanceof Screen.DetailScreen) {
                startFragmentWithStacking(DetailFragment.getInstance(((Screen.DetailScreen) next).fileInfo));
            }
        });
        if (savedInstanceState == null) {
            startFragment(new AccessFragment());
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        permissionsDisposable.set(
                permissionsManager.checkPermissionCommand().observeOn(AndroidSchedulers.mainThread())
                        .subscribe(permissions -> ActivityCompat.requestPermissions(this,
                                permissions,
                                CODE_REQUEST_PERMISSIONS))
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        permissionsDisposable.set(Completable.complete().subscribe());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODE_REQUEST_PERMISSIONS) {
            for (int i = 0; i < permissions.length && i < grantResults.length; i++) {
                permissionsManager.setGranted(permissions[i], grantResults[i] == PackageManager.PERMISSION_GRANTED);
            }
        }
    }

    /**
     * Replace current content fragment with new one
     *
     * @param newFragment fragment to show
     */
    private void startFragment(Fragment newFragment) {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < count; i++) {
            getSupportFragmentManager().popBackStack();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content, newFragment, TAG_FRAGMENT).commit();
    }


    /**
     * Adds current fragment ot back stack and shows new fragment
     *
     * @param newFragment fragment to show
     */
    private void startFragmentWithStacking(Fragment newFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content, newFragment, TAG_FRAGMENT).addToBackStack(null).commit();

    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        if (fragment instanceof BackAware) {
            if (!((BackAware) fragment).onBack()) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    private static final String TAG_FRAGMENT = "currentFragment";
    private static final int CODE_REQUEST_PERMISSIONS = 10101;

}


