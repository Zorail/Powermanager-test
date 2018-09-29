package com.zorail.powermanager.Auth;

import io.reactivex.Completable;

public interface AuthSource {

    Completable createAccount(Credentials cred);
}
