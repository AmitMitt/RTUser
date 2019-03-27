package com.roadTransport.RTUser.walletService;

public class WalletPinRequest {

    private String currentPin;
    private String newPin;
    private String confirmPin;
    private long walletId;

    public String getCurrentPin() {
        return currentPin;
    }

    public void setCurrentPin(String currentPin) {
        this.currentPin = currentPin;
    }

    public String getNewPin() {
        return newPin;
    }

    public void setNewPin(String newPin) {
        this.newPin = newPin;
    }

    public String getConfirmPin() {
        return confirmPin;
    }

    public void setConfirmPin(String confirmPin) {
        this.confirmPin = confirmPin;
    }

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    @Override
    public String toString() {
        return "WalletPinRequest{" +
                "currentPin='" + currentPin + '\'' +
                ", newPin='" + newPin + '\'' +
                ", confirmPin='" + confirmPin + '\'' +
                ", walletId=" + walletId +
                '}';
    }

}
