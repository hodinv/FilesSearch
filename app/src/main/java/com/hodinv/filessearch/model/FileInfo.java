package com.hodinv.filessearch.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class FileInfo {
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
