package com.hodinv.filessearch.screens.detail;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.hodinv.filessearch.model.FileInfo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BindingAdapters {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

    @BindingAdapter("app:date")
    public static void setVisibileByPAttern(TextView view, long date) {
        view.setText(formatter.format(new Date(date)));
    }


    @BindingAdapter("app:image")
    public static void setVisibileByPAttern(ImageView view, FileInfo fileInfo) {
        if (fileInfo.isImage()) {
            Glide.with(view).load(Uri.fromFile(fileInfo.getFile())).into(view);
        }
    }

}
