package com.roadTransport.RTUser.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class DeletedUserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private String userFirstName;

    @Column
    private String userMiddleName;

    @Column
    private String userLastName;

    @Column
    private long userMobileNumber;

    @Column
    private long userAdhaarNumber;

    @Column
    private String userPanNumber;

    @Column
    private boolean userStatus;

    @Column
    private String userCurrentAddress;

    @Column
    private String userPermanentAddress;

    @Column(columnDefinition="TEXT")
    private String userImage;

    @Column
    private String  createdDate;

    @Column(columnDefinition="TEXT")
    private String password;

    @Column(columnDefinition="TEXT")
    private String adhaarImage;

    @Column(columnDefinition="TEXT")
    private String panCardImage;

    @Column
    private String dob;

    @Column
    private long otp;

    public long getOtp() {
        return otp;
    }

    public void setOtp(long otp) {
        this.otp = otp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserMiddleName() {
        return userMiddleName;
    }

    public void setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public long getUserMobileNumber() {
        return userMobileNumber;
    }

    public void setUserMobileNumber(long userMobileNumber) {
        this.userMobileNumber = userMobileNumber;
    }

    public long getUserAdhaarNumber() {
        return userAdhaarNumber;
    }

    public void setUserAdhaarNumber(long userAdhaarNumber) {
        this.userAdhaarNumber = userAdhaarNumber;
    }

    public String getUserPanNumber() {
        return userPanNumber;
    }

    public void setUserPanNumber(String userPanNumber) {
        this.userPanNumber = userPanNumber;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserCurrentAddress() {
        return userCurrentAddress;
    }

    public void setUserCurrentAddress(String userCurrentAddress) {
        this.userCurrentAddress = userCurrentAddress;
    }

    public String getUserPermanentAddress() {
        return userPermanentAddress;
    }

    public void setUserPermanentAddress(String userPermanentAddress) {
        this.userPermanentAddress = userPermanentAddress;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdhaarImage() {
        return adhaarImage;
    }

    public void setAdhaarImage(String adhaarImage) {
        this.adhaarImage = adhaarImage;
    }

    public String getPanCardImage() {
        return panCardImage;
    }

    public void setPanCardImage(String panCardImage) {
        this.panCardImage = panCardImage;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "DeletedUserData{" +
                "id=" + id +
                ", userFirstName='" + userFirstName + '\'' +
                ", userMiddleName='" + userMiddleName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userMobileNumber=" + userMobileNumber +
                ", userAdhaarNumber=" + userAdhaarNumber +
                ", userPanNumber='" + userPanNumber + '\'' +
                ", userStatus=" + userStatus +
                ", userCurrentAddress='" + userCurrentAddress + '\'' +
                ", userPermanentAddress='" + userPermanentAddress + '\'' +
                ", userImage='" + userImage + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", password='" + password + '\'' +
                ", adhaarImage='" + adhaarImage + '\'' +
                ", panCardImage='" + panCardImage + '\'' +
                ", dob='" + dob + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
}
