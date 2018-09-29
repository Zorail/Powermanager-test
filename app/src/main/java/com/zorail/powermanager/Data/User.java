package com.zorail.powermanager.Data;

public class User {
    private String address;
    private String e_board;
    private String power_station;
    private String username;

    public User(String address, String e_board, String power_station, String username) {
        this.address = address;
        this.e_board = e_board;
        this.power_station = power_station;
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getE_board() {
        return e_board;
    }

    public void setE_board(String e_board) {
        this.e_board = e_board;
    }

    public String getPower_station() {
        return power_station;
    }

    public void setPower_station(String power_station) {
        this.power_station = power_station;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
