package com.zorail.powermanager.Data;

public class Slab {
    public int charge;
    public int lower;
    public int upper;

    public Slab(){}

    public Slab(int charge, int lower, int upper) {
        this.charge = charge;
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public String toString() {
        return "Slab{" +
                "charge=" + charge +
                ", lower=" + lower +
                ", upper=" + upper +
                '}';
    }
}
