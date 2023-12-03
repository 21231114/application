package com.example.myapplication.entity;

public class CanteenInfo {
    private String canteen_name;
    private int canteen_id;

    public CanteenInfo(int canteen_id, String canteen_name) {
        this.canteen_name = canteen_name;
        this.canteen_id = canteen_id;
    }

    public String getCanteen_name() {
        return canteen_name;
    }

    public void setCanteen_name(String canteen_name) {
        this.canteen_name = canteen_name;
    }

    public int getCanteen_id() {
        return canteen_id;
    }

    public void setCanteen_id(int canteen_id) {
        this.canteen_id = canteen_id;
    }
}
