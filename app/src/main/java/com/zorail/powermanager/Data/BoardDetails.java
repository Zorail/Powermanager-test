package com.zorail.powermanager.Data;

public class BoardDetails {
    private String name;
    private Slab slabs[];

    public BoardDetails(){}

    public BoardDetails(String name, Slab slabs[]) {
            this.name = name;
            this.slabs = slabs;
    }

    private String getName() {
        return this.name;
    }

    private Slab[] getSlabs() {
        return this.slabs;
    }

    private void setSlabs(Slab slabs[]) {
        this.slabs = slabs;
    }

    private void setName(String name) {
        this.name = name;
    }
}
