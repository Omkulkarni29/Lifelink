package com.om.model;

import java.util.List;

public class ReceiverModel {
    private String name;
    private String bloodGroup;
    private List<String> organs;
    private String urgency;

    public ReceiverModel(String name, String bloodGroup, List<String> organs, String urgency) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.organs = organs;
        this.urgency = urgency;
    }

    public String getName() { return name; }
    public String getBloodGroup() { return bloodGroup; }
    public List<String> getOrgans() { return organs; }
    public String getUrgency() { return urgency; }
}
