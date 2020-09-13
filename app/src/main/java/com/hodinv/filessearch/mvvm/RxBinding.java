package com.hodinv.filessearch.mvvm;


import androidx.annotation.NonNull;
import androidx.databinding.Observable.OnPropertyChangedCallback;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

// take from here https://gist.github.com/SushiFu/7acdfc54145401f6173c09afae2676b5
public class RxBinding {

    private RxBinding() {
    }

    public static <T> Observable<T> toObservableOnUIReset(@NonNull final ObservableField<T> observableField) {
        return toObservableReset(observableField).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable<T> toObservableOnUI(@NonNull final ObservableField<T> observableField) {
        return toObservable(observableField).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable<T> toObservableReset(@NonNull final ObservableField<T> observableField) {
        return toObs(observableField)
                .filter(observableField1 -> observableField1.get() != null)
                .doAfterNext(observableField1 -> observableField1.set(null))
                .map(ObservableField::get);
    }

    public static <T> Observable<T> toObservable(@NonNull final ObservableField<T> observableField) {
        return toObs(observableField)
                .filter(observableField1 -> observableField1.get() != null)
                .map(ObservableField::get);
    }

    private static <T> Observable<ObservableField<T>> toObs(@NonNull final ObservableField<T> observableField) {
        return Observable.create(emitter -> {
            final androidx.databinding.Observable.OnPropertyChangedCallback callback = new androidx.databinding.Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(androidx.databinding.Observable dataBindingObservable, int propertyId) {
                    if (dataBindingObservable == observableField) {
                        emitter.onNext(observableField);
                    }
                }
            };
            observableField.addOnPropertyChangedCallback(callback);
            emitter.setCancellable(() -> observableField.removeOnPropertyChangedCallback(callback));
        });
    }

    public static Observable<Boolean> toObservableOnUIReset(@NonNull final ObservableBoolean observableBoolean) {
        return toObservableReset(observableBoolean).observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Boolean> toObservableOnUI(@NonNull final ObservableBoolean observableBoolean) {
        return toObservable(observableBoolean).observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Boolean> toObservableReset(@NonNull final ObservableBoolean observableBoolean) {
        return toObs(observableBoolean)
                .filter(ObservableBoolean::get)
                .doAfterNext(observableBoolean1 -> observableBoolean1.set(false))
                .map(ObservableBoolean::get);
    }

    public static Observable<Boolean> toObservable(@NonNull final ObservableBoolean observableBoolean) {
        return toObs(observableBoolean).map(ObservableBoolean::get);
    }

    private static Observable<ObservableBoolean> toObs(@NonNull final ObservableBoolean observableBoolean) {
        return Observable.create(emitter -> {
            final OnPropertyChangedCallback callback = new OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(androidx.databinding.Observable dataBindingObservable, int propertyId) {
                    if (dataBindingObservable == observableBoolean) {
                        emitter.onNext(observableBoolean);
                    }
                }
            };
            observableBoolean.addOnPropertyChangedCallback(callback);
            emitter.setCancellable(() -> observableBoolean.removeOnPropertyChangedCallback(callback));
        });
    }

    public static <T> Observable<List<T>> toObservableOnUI(@NonNull final ObservableList<T> observableList) {
        return toObservable(observableList).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable<List<T>> toObservable(@NonNull final ObservableList<T> observableList) {
        return Observable.create(emitter -> {
            final ObservableList.OnListChangedCallback<ObservableList<T>> callback =
                    new ObservableList.OnListChangedCallback<ObservableList<T>>() {
                        @Override
                        public void onChanged(ObservableList<T> dataBindingObservableList) {
                        }

                        @Override
                        public void onItemRangeChanged(ObservableList<T> observableList, int i, int i1) {
                        }

                        @Override
                        public void onItemRangeInserted(ObservableList<T> dataBindingObservableList, int i, int i1) {
                            if (dataBindingObservableList == observableList) {
                                emitter.onNext(observableList);
                            }
                        }

                        @Override
                        public void onItemRangeMoved(ObservableList<T> observableList, int i, int i1, int i2) {
                        }

                        @Override
                        public void onItemRangeRemoved(ObservableList<T> observableList, int i, int i1) {
                        }
                    };
            observableList.addOnListChangedCallback(callback);
            emitter.setCancellable(() -> observableList.removeOnListChangedCallback(callback));
        });
    }

}