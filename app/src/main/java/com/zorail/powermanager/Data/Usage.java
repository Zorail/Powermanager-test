package com.zorail.powermanager.Data;

public class Usage {
    private int b_id;
    private int p_month;
    private int p_slab;
    private int p_units;
    private int start_unit;

    public Usage() {}

    public Usage(int b_id, int p_month, int p_slab, int p_units, int start_unit) {
        this.b_id = b_id;
        this.p_month = p_month;
        this.p_slab = p_slab;
        this.p_units = p_units;
        this.start_unit = start_unit;
    }

    @Override
    public String toString() {
        return "Usage{" +
                "b_id='" + b_id + '\'' +
                ", p_month=" + p_month +
                ", p_slab=" + p_slab +
                ", p_units=" + p_units +
                ", start_unit=" + start_unit +
                '}';
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public int getP_month() {
        return p_month;
    }

    public void setP_month(int p_month) {
        this.p_month = p_month;
    }

    public int getSlab() {
        return p_slab;
    }

    public void setSlab(int p_slab) {
        this.p_slab = p_slab;
    }

    public float getP_units() {
        return p_units;
    }

    public void setP_units(int p_units) {
        this.p_units = p_units;
    }

    public float getStart_unit() {
        return start_unit;
    }

    public void setStart_unit(int start_unit) {
        this.start_unit = start_unit;
    }
}
