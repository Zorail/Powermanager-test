package com.zorail.powermanager.Data;

public class Slab {
    private float charge;
    private int lower;
    private int upper;

    public Slab(){}

    public Slab(float charge, int lower, int upper) {
        this.charge = charge;
        this.lower = lower;
        this.upper = upper;
    }
}
