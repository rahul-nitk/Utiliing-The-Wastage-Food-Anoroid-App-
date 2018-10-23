package com.example.rahul.firebase;

import java.sql.Time;

public class foodDetail {

    public String noOfPeople;
    public String Address;
    public String Time;
    public String type;
    public String email;

    foodDetail()
    {

    }
    foodDetail(String noOfPeople,String Address,String Time,String type,String email)
    {
        this.noOfPeople=noOfPeople;
        this.Address=Address;
        this.Time=Time;
        this.type=type;
        this.email=email;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoOfPeople() {
        return noOfPeople;
    }

    public void setNoOfPeople(String noOfPeople) {
        this.noOfPeople = noOfPeople;
    }

    public String getfoodAddress() {
        return Address;
    }

    public void setfoodAddress(String address) {
        Address = address;
    }

    public String getfoodTime() {
        return Time;
    }

    public void setfoodTime(String time) {
        Time = time;
    }

    public String getfoodType() {
        return type;
    }

    public void setfoodType(String type) {
        this.type = type;
    }
}
