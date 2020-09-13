package com.hodinv.filessearch.services.repository.comparators;

import com.hodinv.filessearch.model.FileInfo;

import java.util.Comparator;

public class NameComparator implements Comparator<FileInfo> {

    @Override
    public int compare(FileInfo o1, FileInfo o2) {
        return o1.fileName.compareTo(o2.fileName);
    }
}
