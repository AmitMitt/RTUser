package com.roadTransport.RTUser.entity;

import javax.persistence.*;

@Table
@Entity
public class MobileNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private long mobileNumber;

    @Column
    private long otpNumber;

    @Column
    private boolean status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public long getOtpNumber() {
        return otpNumber;
    }

    public void setOtpNumber(long otpNumber) {
        this.otpNumber = otpNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MobileNumber{" +
                "id=" + id +
                ", mobileNumber=" + mobileNumber +
                ", otpNumber=" + otpNumber +
                ", status=" + status +
                '}';
    }
}
