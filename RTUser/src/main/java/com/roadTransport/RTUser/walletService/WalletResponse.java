package com.roadTransport.RTUser.walletService;

public class WalletResponse {

    private double balance;
    private String message;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WalletResponse{" +
                "balance=" + balance +
                ", message='" + message + '\'' +
                '}';
    }


}
