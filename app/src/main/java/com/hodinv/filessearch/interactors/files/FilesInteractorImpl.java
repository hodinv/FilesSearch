package com.hodinv.filessearch.interactors.files;

import com.hodinv.filessearch.model.FileInfo;
import com.hodinv.filessearch.services.repository.FilesRepository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class FilesInteractorImpl implements FilesInteractor {

    private final FilesRepository filesRepository;

    public FilesInteractorImpl(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @Override
    public Completable getAllFiles(File topDirectory) {
        return Completable.create(emitter -> {
            filesRepository.clearAll();
            Queue<File> directoriesToProcess = new LinkedList<>();
            directoriesToProcess.add(topDirectory);
            ArrayList<FileInfo> list = new ArrayList<>();

            while (directoriesToProcess.size() > 0 && !emitter.isDisposed()) {
                File nextDirectory = directoriesToProcess.poll();
                File[] files = nextDirectory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isDirectory()) {
                            directoriesToProcess.add(file);
                        } else {

                            // based on https://stackoverflow.com/questions/2389225/android-how-to-get-a-files-creation-date
                            long createdAt = 0;

                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                                createdAt = attr.creationTime().toMillis();
                            } else {
                                createdAt = file.lastModified();
                            }
                            FileInfo fileInfo = new FileInfo(file.getName(), nextDirectory.getPath(), file.length(),
                                    createdAt, file.lastModified());
                            list.add(fileInfo);
                        }
                    }
                    filesRepository.setProgress(list.size());
                }
            }

            if (!emitter.isDisposed()) {
                filesRepository.setList(list);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }
}
