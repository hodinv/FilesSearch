package com.hodinv.filessearch.screens;

import com.hodinv.filessearch.model.FileInfo;

public class Screen {

    private static class AccessScreen extends Screen {

    }

    public static class SearchScreen extends Screen {

    }

    public static class DetailScreen extends Screen {
        public final FileInfo fileInfo;

        public DetailScreen(FileInfo fileInfo) {
            this.fileInfo = fileInfo;
        }
    }

}
