package com.nhnacademy.parkingservicesystem.parkinglot;

import java.util.HashMap;

public class FeeTable {
    HashMap<String,Integer> table = new HashMap<>();

    public FeeTable(HashMap<String, Integer> table) {
        this.table = table;
    }

    public long getFee(String key) {
        return table.get(key);
    }



}
