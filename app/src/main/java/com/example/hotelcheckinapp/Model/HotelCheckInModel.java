package com.example.hotelcheckinapp.Model;

public class HotelCheckInModel {
    String Name;
    String OrganizationalCode;

    public HotelCheckInModel(){

    }

    public HotelCheckInModel(String name, String organizationalCode) {
        Name = name;
        OrganizationalCode = organizationalCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOrganizationalCode() {
        return OrganizationalCode;
    }

    public void setOrganizationalCode(String organizationalCode) {
        OrganizationalCode = organizationalCode;
    }
}
