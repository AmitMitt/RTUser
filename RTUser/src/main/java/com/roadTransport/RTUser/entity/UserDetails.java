package com.roadTransport.RTUser.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
@SQLDelete(sql = "update UserDetails set deleted=true where id=?")
@Where(clause = "deleted=false")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    @Email
    @NotNull
    private String email;

    @Column
    private boolean deleted;

    @Column
    private String userRole;

    @Column
    @NotNull
    private String userName;

    @Column
    @Size(min = 10, max = 10)
    private String userMobileNumber;

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
    private long  createdDate;

    @Column
    private long updatedDate;

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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(long updatedDate) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserMobileNumber() {
        return userMobileNumber;
    }

    public void setUserMobileNumber(String userMobileNumber) {
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
                "id=" + id +
                ", email='" + email + '\'' +
                ", deleted=" + deleted +
                ", userRole='" + userRole + '\'' +
                ", userName='" + userName + '\'' +
                ", userMobileNumber='" + userMobileNumber + '\'' +
                ", userAdhaarNumber=" + userAdhaarNumber +
                ", userPanNumber='" + userPanNumber + '\'' +
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
