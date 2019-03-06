package com.roadTransport.RTUser.serviceImpl;

import com.roadTransport.RTUser.entity.UserTemporaryDetails;
import com.roadTransport.RTUser.model.OtpDetails;
import com.roadTransport.RTUser.model.userRequest.UserRequest;
import com.roadTransport.RTUser.otpService.OtpService;
import com.roadTransport.RTUser.repository.UserTemporaryDetailsPageRepository;
import com.roadTransport.RTUser.repository.UserTemporaryDetailsRepository;
import com.roadTransport.RTUser.service.UserTemporaryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

@Service
public class UserTemporaryDetailsServiceImpl implements UserTemporaryDetailsService {

    @Autowired
    private UserTemporaryDetailsRepository userTemporaryDetailsRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserTemporaryDetailsPageRepository userTemporaryDetailsPageRepository;

    @Override
    public UserTemporaryDetails add(UserRequest userRequest) throws Exception {

        UserTemporaryDetails userTemporaryDetails = userTemporaryDetailsRepository.findByMdn(userRequest.getUserMobileNumber());

        if(userTemporaryDetails!=null){

            userTemporaryDetails.setUserFirstName(userRequest.getUserFirstName());
            userTemporaryDetails.setUserMiddleName(userRequest.getUserMiddleName());
            userTemporaryDetails.setUserLastName(userRequest.getUserLastName());
            userTemporaryDetails.setUserCurrentAddress(userRequest.getUserCurrentAddress());
            userTemporaryDetails.setUserPermanentAddress(userRequest.getUserPermanentAddress());
            userTemporaryDetails.setUserAdhaarNumber(userRequest.getUserAdhaarNumber());
            userTemporaryDetails.setUserMobileNumber(userRequest.getUserMobileNumber());
            userTemporaryDetails.setUserPanNumber(userRequest.getUserPanNumber());
            userTemporaryDetails.setUserImage(Base64.getEncoder().encodeToString(userRequest.getUserImage().getBytes()));
            userTemporaryDetails.setUpdatedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
            userTemporaryDetails.setPassword(Base64.getEncoder().encodeToString(userRequest.getPassword().getBytes()));
            userTemporaryDetails.setAdhaarImage(Base64.getEncoder().encodeToString(userRequest.getUserAdhaarImage().getBytes()));
            userTemporaryDetails.setPanCardImage(Base64.getEncoder().encodeToString(userRequest.getUserPanCardImage().getBytes()));

            OtpDetails otpDetails = otpService.getOtp(userRequest.getUserMobileNumber());
            userTemporaryDetails.setOtp(otpDetails.getOtpNumber());
            userTemporaryDetailsRepository.saveAndFlush(userTemporaryDetails);
            return userTemporaryDetails;
        }

        else {

            UserTemporaryDetails userTemporaryDetails1 = new UserTemporaryDetails();
            userTemporaryDetails1.setUserFirstName(userRequest.getUserFirstName());
            userTemporaryDetails1.setUserMiddleName(userRequest.getUserMiddleName());
            userTemporaryDetails1.setUserLastName(userRequest.getUserLastName());
            userTemporaryDetails1.setUserCurrentAddress(userRequest.getUserCurrentAddress());
            userTemporaryDetails1.setUserPermanentAddress(userRequest.getUserPermanentAddress());
            userTemporaryDetails1.setUserAdhaarNumber(userRequest.getUserAdhaarNumber());
            userTemporaryDetails1.setUserMobileNumber(userRequest.getUserMobileNumber());
            userTemporaryDetails1.setUserPanNumber(userRequest.getUserPanNumber());
            userTemporaryDetails1.setUserImage(Base64.getEncoder().encodeToString(userRequest.getUserImage().getBytes()));
            userTemporaryDetails1.setUserStatus(false);
            userTemporaryDetails1.setCreatedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
            userTemporaryDetails1.setPassword(Base64.getEncoder().encodeToString(userRequest.getPassword().getBytes()));
            userTemporaryDetails1.setAdhaarImage(Base64.getEncoder().encodeToString(userRequest.getUserAdhaarImage().getBytes()));
            userTemporaryDetails1.setPanCardImage(Base64.getEncoder().encodeToString(userRequest.getUserPanCardImage().getBytes()));

            OtpDetails otpDetails = otpService.getOtp(userRequest.getUserMobileNumber());
            userTemporaryDetails1.setOtp(otpDetails.getOtpNumber());

            userTemporaryDetailsRepository.saveAndFlush(userTemporaryDetails1);

            return userTemporaryDetails1;
        }
    }


    @Override
    public UserTemporaryDetails getListByMdn(long userMobileNumber) throws Exception {

        UserTemporaryDetails userTemporaryDetails = userTemporaryDetailsRepository.findByMdn(userMobileNumber);

        if(userTemporaryDetails == null){
            throw new Exception("Empty Data.");
        }
        return userTemporaryDetails;
    }

    @Override
    public UserTemporaryDetails getOtp(long userMobileNumber) throws Exception {

        UserTemporaryDetails userTemporaryDetails = userTemporaryDetailsRepository.findByMdn(userMobileNumber);

        if(userTemporaryDetails == null){
            throw new Exception("Empty Data.");
        }
        return userTemporaryDetails;
    }

    @Override
    public Page<UserTemporaryDetails> listAllByPage(Pageable pageable){
       return userTemporaryDetailsPageRepository.findAll(pageable);
    }
}