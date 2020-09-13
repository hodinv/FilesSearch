package com.hodinv.filessearch.services.notification;

import android.app.Notification;

import com.hodinv.filessearch.model.SearchInfo;

public interface NotificationManager {
    // creates pending notification with initial state (same as epty search with no files found
    Notification getNotification();

    /**
     * update notification to proper state
     *
     * @param searchInfo if empty search - just info about found files. if not empty search - also shows what and howmany found
     */
    void updateNotification(SearchInfo searchInfo);

    int getNotificationId();
}
