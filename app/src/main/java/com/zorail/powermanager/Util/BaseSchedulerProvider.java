package com.zorail.powermanager.Util;

import io.reactivex.Scheduler;

public interface BaseSchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
