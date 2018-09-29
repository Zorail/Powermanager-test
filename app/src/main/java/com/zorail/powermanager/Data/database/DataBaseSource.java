package com.zorail.powermanager.Data.database;

import android.util.Pair;

import com.zorail.powermanager.Data.BoardDetails;
import com.zorail.powermanager.Data.Usage;
import com.zorail.powermanager.Data.User;

import io.reactivex.Maybe;
import io.reactivex.Observable;

public interface DataBaseSource {
//    Maybe<BoardDetails> getBoardDetails(String phone);

    Maybe<User> getUserDetails(String phone);
    Observable<Usage> getUsageDetails(String phone);
    Observable<BoardDetails> getBoardDetails(String boardId);
    Observable<Pair<Usage, BoardDetails>> combinedUsageBoardDetails(String phone);
}
