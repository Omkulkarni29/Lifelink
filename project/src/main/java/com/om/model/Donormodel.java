package com.om.model;

import java.util.List;

public class Donormodel {
    private String name;
    private String dob;
    private String gender;
    private String bloodGroup;
    private List<String> organs;

    public Donormodel(String name, String dob, String gender, String bloodGroup, List<String> organs) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.organs = organs;
    }

    public String getName() { return name; }
    public String getDob() { return dob; }
    public String getGender() { return gender; }
    public String getBloodGroup() { return bloodGroup; }
    public List<String> getOrgans() { return organs; }
}
