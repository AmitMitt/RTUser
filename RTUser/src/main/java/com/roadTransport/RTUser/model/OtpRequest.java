package com.roadTransport.RTUser.model;

public class OtpRequest {

    private long otp;
    private long userMobileNumber;

    public long getOtp() {
        return otp;
    }

    public void setOtp(long otp) {
        this.otp = otp;
    }

    public long getUserMobileNumber() {
        return userMobileNumber;
    }

    public void setUserMobileNumber(long userMobileNumber) {
        this.userMobileNumber = userMobileNumber;
    }
}
