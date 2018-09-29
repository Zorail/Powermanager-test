package com.zorail.powermanager.Home;

import android.util.Log;
import android.util.Pair;

import com.zorail.powermanager.Data.BoardDetails;
import com.zorail.powermanager.Data.Usage;
import com.zorail.powermanager.Data.User;
import com.zorail.powermanager.Data.database.DataBaseSource;
import com.zorail.powermanager.Util.SchedulerProvider;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.observable.ObservableJust;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableObserver;

public class HomePresenter implements HomeContract.Presenter {

    private CompositeDisposable disposable;
    private HomeContract.View view;
    private DataBaseSource dataBaseSource;
    private SchedulerProvider schedulerProvider;

    HomePresenter(HomeContract.View view, CompositeDisposable disposable, DataBaseSource dataBaseSource, SchedulerProvider schedulerProvider) {
        this.view = view;
        this.disposable = disposable;
        this.dataBaseSource = dataBaseSource;
        this.schedulerProvider = schedulerProvider;
        view.setPresenter(this);
    }

    @Override
    public void getUser(String phone) {
        view.showProgressIndicator(true);
        disposable.add(
                dataBaseSource.getUserDetails(phone)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribeWith(new DisposableMaybeObserver<User>() {
                        @Override
                        public void onSuccess(User user) {
                            view.showProgressIndicator(false);

                        }

                        @Override
                        public void onError(Throwable e) {
                            view.showProgressIndicator(false);
                            view.makeToast(e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    })
        );
    }

    @Override
    public void getUsersUsage(String phone) {
        view.showProgressIndicator(true);
        disposable.add(
                dataBaseSource.combinedUsageBoardDetails(phone)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableObserver<Pair<Usage, BoardDetails>>() {
                    @Override
                    public void onNext(Pair<Usage, BoardDetails> usageBoardDetailsPair) {
                        Log.d("Combined", usageBoardDetailsPair.toString());
                        view.showProgressIndicator(false);
                        view.setUsageDetails(usageBoardDetailsPair.first, usageBoardDetailsPair.second);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showProgressIndicator(false);
                        view.makeToast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );
//        view.showProgressIndicator(true);
//        disposable.add(
//                dataBaseSource.getUsageDetails(phone)
//                        .subscribeOn(schedulerProvider.io())
//                        .observeOn(schedulerProvider.ui())
//                        .flatMap(usage -> dataBaseSource.getBoardDetails(String.valueOf(usage.getB_id())))
//                        .subscribeWith(new DisposableObserver<Usage>() {
//                            @Override
//                            public void onNext(Usage usage) {
//                                view.showProgressIndicator(false);
//                                view.setUsageDetails(usage);
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                view.showProgressIndicator(false);
//                                view.makeToast(e.getMessage());
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        })
//        );
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        disposable.clear();
    }
}
