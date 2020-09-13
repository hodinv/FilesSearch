package com.hodinv.filessearch.mvvm;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel<Router extends MvvmRouter> extends ViewModel {
    protected final Router router;

    private final CompositeDisposable disposable = new CompositeDisposable();

    public BaseViewModel(Router router) {
        this.router = router;
    }

    protected void addDisposable(Disposable disposable) {
        this.disposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

}
