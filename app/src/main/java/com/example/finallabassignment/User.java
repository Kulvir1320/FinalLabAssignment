package com.example.finallabassignment;

public class User {
    int id;
    String firstname, lastName,Address;
    int phone;

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return Address;
    }

    public int getPhone() {
        return phone;
    }

    public User(int id, String firstname, String lastName, String address, int phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastName = lastName;
        Address = address;
        this.phone = phone;
    }
}
