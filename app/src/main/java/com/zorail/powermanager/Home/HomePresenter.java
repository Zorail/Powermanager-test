package com.zorail.powermanager.Home;

import com.zorail.powermanager.Data.User;
import com.zorail.powermanager.Data.database.DataBaseSource;
import com.zorail.powermanager.Util.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableMaybeObserver;

public class HomePresenter implements HomeContract.Presenter {

    private CompositeDisposable disposable;
    private HomeContract.View view;
    private DataBaseSource dataBaseSource;
    private SchedulerProvider schedulerProvider;

    public HomePresenter(HomeContract.View view, CompositeDisposable disposable, DataBaseSource dataBaseSource, SchedulerProvider schedulerProvider) {
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
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        disposable.clear();
    }
}
