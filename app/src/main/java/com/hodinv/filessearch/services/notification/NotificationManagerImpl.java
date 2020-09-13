package com.hodinv.filessearch.services.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.MainThread;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.hodinv.filessearch.MainActivity;
import com.hodinv.filessearch.R;
import com.hodinv.filessearch.model.SearchInfo;

import static android.content.Context.NOTIFICATION_SERVICE;

// based on https://stackoverflow.com/questions/52552450/how-to-start-a-background-service-in-oreo
public class NotificationManagerImpl implements NotificationManager {

    private final Context context;
    private NotificationCompat.Builder builder = null;


    public NotificationManagerImpl(Context context) {
        this.context = context;
    }

    @Override
    @MainThread
    public Notification getNotification() {
        if (builder == null) {
            if (Build.VERSION.SDK_INT > 26) {
                builder = getNotificationBuilder(createChannel());
            } else {
                builder = getNotificationBuilder(null);
            }
        }
        return builder.build();
    }

    @Override
    @MainThread
    public void updateNotification(SearchInfo searchInfo) {
        if (builder == null) {
            getNotification();
        }
        String title = "Getting files list";
        String text = "Files: " + searchInfo.total;
        if (!searchInfo.search.isEmpty() && searchInfo.total > 0) {
            title = "Search : " + searchInfo.search;
            text = "Found " + searchInfo.found + " in " + searchInfo.total + " files";
        }
        builder.setContentTitle(title)
                .setContentText(text);

        android.app.NotificationManager manager = (android.app.NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(getNotificationId(), builder.build());
        }
    }

    @Override
    public int getNotificationId() {
        return 101;
    }


    private NotificationCompat.Builder getNotificationBuilder(String channelId) {

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_app_running);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        if (channelId != null) {
            builder.setChannelId(channelId);
        }

        return builder.setContentTitle("Getting files list")
                .setTicker("Searching...")
                .setContentText("Pending...")
                .setSmallIcon(R.drawable.ic_app_running)
                .setLargeIcon(icon)
                .setContentIntent(getNotificationIntent())
                .setOngoing(true);
    }

    private PendingIntent getNotificationIntent() {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(context, 0, notificationIntent, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String createChannel() {
        String CHANNEL_ONE_ID = "Package.Service";
        String CHANNEL_ONE_NAME = "Screen service";
        NotificationChannel notificationChannel = null;
        notificationChannel = new NotificationChannel(CHANNEL_ONE_ID,
                CHANNEL_ONE_NAME, android.app.NotificationManager.IMPORTANCE_MIN);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setShowBadge(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        android.app.NotificationManager manager = (android.app.NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.createNotificationChannel(notificationChannel);
        }
        return CHANNEL_ONE_ID;
    }
}
