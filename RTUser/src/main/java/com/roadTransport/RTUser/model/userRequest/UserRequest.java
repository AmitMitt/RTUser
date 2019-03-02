package com.roadTransport.RTUser.model.userRequest;

public class UserRequest {

    private String userFirstName;
    private String userMiddleName;
    private String userLastName;
    private long userMobileNumber;
    private long userAdhaarNumber;
    private long userPanNumber;
    private String userCurrentAddress;
    private String userPermanentAddress;
    private String userImage;
    private String password;
    private long otp;
    private String userAdhaarImage;
    private String userPanCardImage;

    String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*@#$%^&+=])(?=\\S+$).{8,}";

    public String getUserAdhaarImage() {
        return userAdhaarImage;
    }

    public void setUserAdhaarImage(String userAdhaarImage) {
        this.userAdhaarImage = userAdhaarImage;
    }

    public String getUserPanCardImage() {
        return userPanCardImage;
    }

    public void setUserPanCardImage(String userPanCardImage) {
        this.userPanCardImage = userPanCardImage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = String.valueOf(password.matches(pattern));
    }

    public long getOtp() {
        return otp;
    }

    public void setOtp(long otp) {
        this.otp = otp;
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
        return "UserRequest{" +
                "userFirstName='" + userFirstName + '\'' +
                ", userMiddleName='" + userMiddleName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userMobileNumber=" + userMobileNumber +
                ", userAdhaarNumber=" + userAdhaarNumber +
                ", userPanNumber=" + userPanNumber +
                ", userCurrentAddress='" + userCurrentAddress + '\'' +
                ", userPermanentAddress='" + userPermanentAddress + '\'' +
                ", userImage='" + userImage + '\'' +
                ", password='" + password + '\'' +
                ", otp=" + otp +
                ", userAdhaarImage='" + userAdhaarImage + '\'' +
                ", userPanCardImage='" + userPanCardImage + '\'' +
                ", pattern='" + pattern + '\'' +
                '}';
    }
}