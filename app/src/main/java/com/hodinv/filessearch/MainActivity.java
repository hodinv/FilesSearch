package com.hodinv.filessearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.hodinv.filessearch.screens.Screen;
import com.hodinv.filessearch.screens.access.AccessFragment;
import com.hodinv.filessearch.screens.search.SearchFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {


    @Inject
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApplicationFileSearch.get(this).injectMainActivity(this);
        viewModel.nextScreen.observe(this, next -> {
            if (next instanceof Screen.SearchScreen) {
                startFragmentWithStacking(new SearchFragment());
            }
        });
        if (savedInstanceState == null) {
            startFragment(new AccessFragment());
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(Build.VERSION.SDK_INT >25){
            startForegroundService(new Intent(this, MainService.class));
        }else{
            startService(new Intent(this, MainService.class));
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

    private static String TAG_FRAGMENT = "currentFragment";

}
