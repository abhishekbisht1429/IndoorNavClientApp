package com.example.hp.indoornavclientapp.model;

public class WapModel {
    private String building;
    private String macAddr;
    private String floor;

    WapModel() {

    }
    WapModel(String building,String macAddr,String floor) {
        this.building = building;
        this.macAddr = macAddr;
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
}
