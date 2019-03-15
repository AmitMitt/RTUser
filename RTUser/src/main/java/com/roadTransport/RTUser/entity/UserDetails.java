package com.roadTransport.RTUser.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long userId;

    @Column
    @NotNull
    private String userFirstName;

    @Column
    private String userMiddleName;

    @Column
    private String userLastName;

    @Column(unique = true,length = 10)
    private long userMobileNumber;

    @Column
    @NotNull
    private long userAdhaarNumber;

    @Column
    @NotNull
    private String userPanNumber;

    @Column
    private boolean userStatus;

    @Column
    private String userCurrentAddress;

    @Column
    private String userPermanentAddress;

    @Column
    private boolean kycStatus;

    @Column(columnDefinition="TEXT")
    private String userImage;

    @Column
    private String  createdDate;

    @Column
    private String updatedDate;

    @Column(columnDefinition="TEXT")
    @NotNull
    private String password;

    @Column(columnDefinition="TEXT")
    private String adhaarImage;

    @Column(columnDefinition="TEXT")
    private String panCardImage;

    @Column
    @NotNull
    private String dob;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(boolean kycStatus) {
        this.kycStatus = kycStatus;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "UserDetails{" +
                "userId=" + userId +
                ", userFirstName='" + userFirstName + '\'' +
                ", userMiddleName='" + userMiddleName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userMobileNumber=" + userMobileNumber +
                ", userAdhaarNumber=" + userAdhaarNumber +
                ", userPanNumber=" + userPanNumber +
                ", userStatus=" + userStatus +
                ", userCurrentAddress='" + userCurrentAddress + '\'' +
                ", userPermanentAddress='" + userPermanentAddress + '\'' +
                ", kycStatus=" + kycStatus +
                ", userImage='" + userImage + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                ", password='" + password + '\'' +
                ", adhaarImage='" + adhaarImage + '\'' +
                ", panCardImage='" + panCardImage + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
