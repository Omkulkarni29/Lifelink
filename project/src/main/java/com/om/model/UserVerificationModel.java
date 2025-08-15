package com.om.model;

import java.util.List;

public class UserVerificationModel {
    private String uid; // Stores the document ID for updates
    private String name;
    private String email;
    private String username;
    private String mobileNumber;
    private String dob;
    private String gender;
    private String bloodGroup;
    private List<String> organs; // For donors/receivers
    private String role; // "donor" or "receiver"
    private boolean isApproved; // Crucial for verification
    private String status; // "pending", "approved", "rejected"
    private String rejectionReason; // Reason for rejection

    public UserVerificationModel(String uid, String name, String email, String username, String mobileNumber, String dob, String gender, String bloodGroup, List<String> organs, String role, boolean isApproved, String status, String rejectionReason) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.dob = dob;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.organs = organs;
        this.role = role;
        this.isApproved = isApproved;
        this.status = status;
        this.rejectionReason = rejectionReason;
    }

    // --- Getters ---
    public String getUid() { return uid; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getMobileNumber() { return mobileNumber; }
    public String getDob() { return dob; }
    public String getGender() { return gender; }
    public String getBloodGroup() { return bloodGroup; }
    public List<String> getOrgans() { return organs; }
    public String getRole() { return role; }
    public boolean getIsApproved() { return isApproved; }
    public String getStatus() { return status; }
    public String getRejectionReason() { return rejectionReason; }

    // Optional Setters (if needed elsewhere to modify the model after creation)
    public void setIsApproved(boolean approved) { this.isApproved = approved; }
    public void setStatus(String status) { this.status = status; }
    public void setRejectionReason(String reason) { this.rejectionReason = reason; }
}
