package com.zorail.powermanager.Home;

import com.zorail.powermanager.BasePresenter;
import com.zorail.powermanager.BaseView;

public interface HomeContract {
    interface View extends BaseView<Presenter> {

        @Override
        void setPresenter(Presenter presenter);

        @Override
        void makeToast(int stringId);

        @Override
        void makeToast(String message);

        void showProgressIndicator(Boolean show);
    }

    interface Presenter extends BasePresenter {
        void getUser(String phone);
    }
}
