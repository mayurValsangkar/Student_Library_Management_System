package com.example.Student_Library_Management_System.DTOs;

public class AuthorEntryDto {

    // This is just an object that will be used to take request

    // It will contain the parameters that we want to send from postman

    // id is not here  because we don't want to send it from postman


    private String name;

    private int age;

    private String country;

    private double ratings;

    public AuthorEntryDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }
}
