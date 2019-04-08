package com.roadTransport.RTUser.walletService;

public class WalletRequest {

    private String ownerName;
    private long walletId;
    private long walletPin;
    private double balance;
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    public long getWalletPin() {
        return walletPin;
    }

    public void setWalletPin(long walletPin) {
        this.walletPin = walletPin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "WalletRequest{" +
                "ownerName='" + ownerName + '\'' +
                ", walletId=" + walletId +
                ", walletPin=" + walletPin +
                ", balance=" + balance +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
