package com.zorail.powermanager.Data.database;

import com.zorail.powermanager.Data.BoardDetails;
import com.zorail.powermanager.Data.Usage;
import com.zorail.powermanager.Data.User;

import io.reactivex.Maybe;

public interface DataBaseSource {
    Maybe<BoardDetails> getBoardDetails(String phone);

    Maybe<User> getUserDetails(String phone);
    Maybe<Usage> getUsageDetails(String phone);
}
