package com.hodinv.filessearch;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.hodinv.filessearch.interactors.FilesInteractor;
import com.hodinv.filessearch.services.permissions.PermissionsManager;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainService extends Service {

    @Inject
    FilesInteractor filesInteractor;

    @Inject
    PermissionsManager permissionsManager;

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

        disposables.add(permissionsManager.areAllPermissionsReady().subscribeOn(Schedulers.io())
                .distinctUntilChanged()
                .switchMap(state -> {
                    if (state) {
                        return Observable.empty();
                        //return filesInteractor.getAllFiles(permissionsManager.topAccesibleFile());
                    } else {
                        return Observable.empty();
                    }
                }).subscribe());



        startRunningInForeground();

        // todo: subscribe for state
        // todo: subscribe for search
    }

    // based on https://stackoverflow.com/questions/52552450/how-to-start-a-background-service-in-oreo

    private void startRunningInForeground() {

        //if more than or equal to 26
        if (Build.VERSION.SDK_INT >= 26) {

            //if more than 26
            if(Build.VERSION.SDK_INT > 26){
                String CHANNEL_ONE_ID = "Package.Service";
                String CHANNEL_ONE_NAME = "Screen service";
                NotificationChannel notificationChannel = null;
                notificationChannel = new NotificationChannel(CHANNEL_ONE_ID,
                        CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_MIN);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.setShowBadge(true);
                notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                if (manager != null) {
                    manager.createNotificationChannel(notificationChannel);
                }

                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_app_running);
                Notification notification = new Notification.Builder(getApplicationContext())
                        .setChannelId(CHANNEL_ONE_ID)
                        .setContentTitle("Recording data")
                        .setContentText("App is running background operations")
                        .setSmallIcon(R.drawable.ic_app_running)
                        .setLargeIcon(icon)
                        .build();

                Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                notification.contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);

                startForeground(101, notification);
            }
            //if version 26
            else{
                startForeground(101, updateNotification());
            }
        }
        //if less than version 26
        else{
            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("App")
                    .setContentText("App is running background operations")
                    .setSmallIcon(R.drawable.ic_app_running)
                    .setOngoing(true).build();

            startForeground(101, notification);
        }
    }

    private Notification updateNotification() {

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        return new NotificationCompat.Builder(this)
                .setContentTitle("Activity log")
                .setTicker("Ticker")
                .setContentText("app is running background operations")
                .setSmallIcon(R.drawable.ic_app_running)
                .setContentIntent(pendingIntent)
                .setOngoing(true).build();
    }
}
