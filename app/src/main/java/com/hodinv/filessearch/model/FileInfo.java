package com.hodinv.filessearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.Objects;

public class FileInfo implements Parcelable {
    @NonNull
    public final String fileName;
    @NonNull
    public final String path;

    @NonNull
    public final String fileExtention;

    public final long size;
    public final long createdAt;
    public final long modifiedAt;


    public FileInfo(@NonNull String fileName, @NonNull String path, long size, long createdAt, long modifiedAt) {
        this.fileName = fileName;
        this.path = path;
        this.size = size;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        int point = fileName.lastIndexOf('.');
        fileExtention = point != -1 ? fileName.substring(point) : "";
    }


    protected FileInfo(Parcel in) {
        fileName = in.readString();
        path = in.readString();
        fileExtention = in.readString();
        size = in.readLong();
        createdAt = in.readLong();
        modifiedAt = in.readLong();
    }

    public boolean isImage() {
        return fileExtention.toLowerCase().equals(".jpg") || fileExtention.toLowerCase().equals(".png");
    }

    public File getFile() {
        return new File(path + File.separator + fileName);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileName);
        dest.writeString(path);
        dest.writeString(fileExtention);
        dest.writeLong(size);
        dest.writeLong(createdAt);
        dest.writeLong(modifiedAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FileInfo> CREATOR = new Creator<FileInfo>() {
        @Override
        public FileInfo createFromParcel(Parcel in) {
            return new FileInfo(in);
        }

        @Override
        public FileInfo[] newArray(int size) {
            return new FileInfo[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileInfo fileInfo = (FileInfo) o;
        return size == fileInfo.size &&
                createdAt == fileInfo.createdAt &&
                modifiedAt == fileInfo.modifiedAt &&
                fileName.equals(fileInfo.fileName) &&
                path.equals(fileInfo.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, path, size, createdAt, modifiedAt);
    }
}
