package com.simplecrud.simplecrud;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Employee")
public class Employee {
    private String name;
    private String email;
    private String phoneNumber;
    @Id
    private int serialID;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSerialID() {
        return serialID;
    }

    public void setSerialID(int serialID) {
        this.serialID = serialID;
    }


    public Employee(String name, String email, String phoneNumber, int serialID) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.serialID = serialID;
    }

    public void updateEmployeeRegistry(String name, String email, String phoneNumber, int serialID) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.serialID = serialID;
    }

    public String getInfo(){
        return ("Name: " + this.name + " E-mail: " + this.email + " Phone: " + this.phoneNumber + " ID: " + this.serialID);
    }

}
