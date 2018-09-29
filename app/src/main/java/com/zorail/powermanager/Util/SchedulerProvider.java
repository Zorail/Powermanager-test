package com.zorail.powermanager.Util;

import io.reactivex.Scheduler;

public class SchedulerProvider implements BaseSchedulerProvider{

    @Override
    public Scheduler computation() {
        return null;
    }

    @Override
    public Scheduler io() {
        return null;
    }

    @Override
    public Scheduler ui() {
        return null;
    }
}
