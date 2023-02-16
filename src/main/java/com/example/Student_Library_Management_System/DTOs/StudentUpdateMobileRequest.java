package com.example.Student_Library_Management_System.DTOs;

public class StudentUpdateMobileRequest {

    private int id;

    private String mobileNumber;

    // DTO's depend on the API's being called

    public StudentUpdateMobileRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
