package com.hodinv.filessearch.services.repository.comparators;

import com.hodinv.filessearch.model.FileInfo;

import java.util.Comparator;

public class ExtComparator implements Comparator<FileInfo> {

    @Override
    public int compare(FileInfo o1, FileInfo o2) {
        return o1.fileExtention.compareTo(o2.fileExtention);
    }
}
