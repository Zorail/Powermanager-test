package com.zorail.powermanager.Home;

import com.zorail.powermanager.BasePresenter;
import com.zorail.powermanager.BaseView;
import com.zorail.powermanager.Data.Usage;

public interface HomeContract {
    interface View extends BaseView<Presenter> {

        @Override
        void setPresenter(Presenter presenter);

        @Override
        void makeToast(int stringId);

        @Override
        void makeToast(String message);

        void showProgressIndicator(Boolean show);

        void setUsageDetails(Usage usage);
    }

    interface Presenter extends BasePresenter {
        void getUser(String phone);

        void getUsersUsage(String phone);
    }
}
