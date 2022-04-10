package com.example.hotelcheckinapp;

public class HotelCheckInModel {
    private String Name;

    public HotelCheckInModel(){

    }
    public HotelCheckInModel(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
