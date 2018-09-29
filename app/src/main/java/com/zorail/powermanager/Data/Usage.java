package com.zorail.powermanager.Data;

public class Usage {
    private String b_id;
    private int p_month;
    private int p_slab;
    private float p_units;
    private float start_unit;

    public Usage(String b_id, int p_month, int p_slab, float p_units, float start_unit) {
        this.b_id = b_id;
        this.p_month = p_month;
        this.p_slab = p_slab;
        this.p_units = p_units;
        this.start_unit = start_unit;
    }

    public String getB_id() {
        return b_id;
    }

    public void setB_id(String b_id) {
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

    public void setP_units(float p_units) {
        this.p_units = p_units;
    }

    public float getStart_unit() {
        return start_unit;
    }

    public void setStart_unit(float start_unit) {
        this.start_unit = start_unit;
    }
}
