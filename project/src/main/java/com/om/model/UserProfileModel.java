package com.om.model;

public class UserProfileModel {
    private String name;
    private String email;
    private String username;
    private String mobileNumber;
    private String dob;
    private String gender;
    private String bloodGroup;

    public UserProfileModel(String name, String email, String username, String mobileNumber, String dob, String gender, String bloodGroup) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.dob = dob;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
    }

    // Getters for all fields
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getMobileNumber() { return mobileNumber; }
    public String getDob() { return dob; }
    public String getGender() { return gender; }
    public String getBloodGroup() { return bloodGroup; }
}
