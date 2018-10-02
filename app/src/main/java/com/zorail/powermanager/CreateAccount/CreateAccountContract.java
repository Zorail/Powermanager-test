package com.zorail.powermanager.CreateAccount;

import com.zorail.powermanager.BasePresenter;
import com.zorail.powermanager.BaseView;

public interface CreateAccountContract {

    interface View extends BaseView<Presenter> {
        @Override
        void setPresenter(Presenter presenter);

        @Override
        void makeToast(String message);

        @Override
        void makeToast(int stringId);

        void showProgressIndicator(Boolean show);

        void setCodeAutomatically(String code);

        void createAccount(Credentials credentials);

        void startHomeActivity(Boolean bool);
    }

    interface Presenter extends BasePresenter {
        @Override
        void subscribe();

        @Override
        void unsubscribe();

        void sendVerificationCode(String number);
    }
}
