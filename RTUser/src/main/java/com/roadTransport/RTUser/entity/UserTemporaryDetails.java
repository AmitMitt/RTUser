package com.roadTransport.RTUser.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table
public class UserTemporaryDetails {

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

    @Column(unique = true)
    @Size(max = 10, min = 10)
    private long userMobileNumber;

    @Column
    @NotNull
    private long userAdhaarNumber;

    @Column
    @NotNull
    private long userPanNumber;

    @Column
    private boolean userStatus;

    @Column
    private String userCurrentAddress;

    @Column
    private String userPermanentAddress;

    @Column(columnDefinition="TEXT")
    private String userImage;

    @Column
    private long otp;

    @Column
    private String createdDate;

    @Column
    private String  updatedDate;

    @Column
    @NotNull
    private String password;

    @Column
    private String adhaarImage;

    @Column
    private String panCardImage;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public long getUserPanNumber() {
        return userPanNumber;
    }

    public void setUserPanNumber(long userPanNumber) {
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

    public long getOtp() {
        return otp;
    }

    public void setOtp(long otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "UserTemporaryDetails{" +
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
                ", userImage='" + userImage + '\'' +
                ", otp=" + otp +
                ", createdDate='" + createdDate + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                ", password='" + password + '\'' +
                ", adhaarImage='" + adhaarImage + '\'' +
                ", panCardImage='" + panCardImage + '\'' +
                '}';
    }
}
