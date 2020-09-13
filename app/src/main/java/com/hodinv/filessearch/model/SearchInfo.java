package com.hodinv.filessearch.model;

public class SearchInfo {
    public final String search;
    public final int found;
    public final int total;
    public final boolean done;

    public SearchInfo(String search, int found, int total, boolean done) {
        this.search = search;
        this.found = found;
        this.total = total;
        this.done = done;
    }
}
