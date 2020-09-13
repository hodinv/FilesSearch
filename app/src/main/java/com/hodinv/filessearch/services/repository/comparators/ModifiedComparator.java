package com.hodinv.filessearch.services.repository.comparators;

import com.hodinv.filessearch.model.FileInfo;

import java.util.Comparator;

public class ModifiedComparator implements Comparator<FileInfo> {

    @Override
    public int compare(FileInfo o1, FileInfo o2) {
        return Long.compare(o1.modifiedAt, o2.modifiedAt);
    }
}
