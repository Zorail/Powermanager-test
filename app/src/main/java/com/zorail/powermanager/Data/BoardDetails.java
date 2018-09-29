package com.zorail.powermanager.Data;

import java.util.List;

public class BoardDetails {
    public String name;
//    public Slab slabs[];
    public List<Slab> slabs;

    @Override
    public String toString() {
        return "BoardDetails{" +
                "name='" + name + '\'' +
                ", slabs=" + slabs +
                '}';
    }

    public BoardDetails(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoardDetails(String name, List<Slab> slabs) {
        this.name = name;
        this.slabs = slabs;
    }

    public List<Slab> getSlabs() {
        return slabs;

    }

    public void setSlabs(List<Slab> slabs) {
        this.slabs = slabs;
    }
}
