package com.roadTransport.RTUser.model.userResponse;

public class UserResponse {

    private String message;

    private long otp;

    private String walletPin;

    public String getWalletPin() {
        return walletPin;
    }

    public void setWalletPin(String walletPin) {
        this.walletPin = walletPin;
    }

    public long getOtp() {
        return otp;
    }

    public void setOtp(long otp) {
        this.otp = otp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "message='" + message + '\'' +
                ", otp=" + otp +
                ", walletPin='" + walletPin + '\'' +
                '}';
    }
}
