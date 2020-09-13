package com.hodinv.filessearch.mvvm;

public interface BackAware {
    /**
     * Marks fragments that process back inside
     *
     * @return true if fragment consumes back and we do not need to process it any more
     */
    boolean onBack();
}
