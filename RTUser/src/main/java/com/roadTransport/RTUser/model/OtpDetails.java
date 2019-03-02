package com.roadTransport.RTUser.model;


public class OtpDetails {

    private long Id;
    private long otpNumber;
    private long otpStartTime;
    private long otpEndTime;
    private String otpFor;
    private long userMobileNumber;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getOtpNumber() {
        return otpNumber;
    }

    public void setOtpNumber(long otpNumber) {
        this.otpNumber = otpNumber;
    }

    public long getOtpStartTime() {
        return otpStartTime;
    }

    public void setOtpStartTime(long otpStartTime) {
        this.otpStartTime = otpStartTime;
    }

    public long getOtpEndTime() {
        return otpEndTime;
    }

    public void setOtpEndTime(long otpEndTime) {
        this.otpEndTime = otpEndTime;
    }

    public String getOtpFor() {
        return otpFor;
    }

    public void setOtpFor(String otpFor) {
        this.otpFor = otpFor;
    }

    public long getUserMobileNumber() {
        return userMobileNumber;
    }

    public void setUserMobileNumber(long userMobileNumber) {
        this.userMobileNumber = userMobileNumber;
    }
}
