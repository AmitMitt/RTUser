package com.roadTransport.RTUser.serviceImpl;

import com.roadTransport.RTUser.entity.UserDetails;
import com.roadTransport.RTUser.entity.UserTemporaryDetails;
import com.roadTransport.RTUser.model.OtpRequest;
import com.roadTransport.RTUser.model.userRequest.UserRequest;
import com.roadTransport.RTUser.otpService.OtpService;
import com.roadTransport.RTUser.repository.UserDetailsPageRepository;
import com.roadTransport.RTUser.repository.UserDetailsRepository;
import com.roadTransport.RTUser.repository.UserTemporaryDetailsRepository;
import com.roadTransport.RTUser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserTemporaryDetailsRepository userTemporaryDetailsRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserDetailsPageRepository userDetailsPageRepository;

    @Override
    public UserDetails add(OtpRequest otpRequest) throws Exception {
        UserTemporaryDetails userTemporaryDetails = userTemporaryDetailsRepository.findByMdn(otpRequest.getUserMobileNumber());

        if(userTemporaryDetails == null){
            throw new Exception("User Data is not Available.");
        }

        boolean verify = otpService.verify(otpRequest.getOtp(),otpRequest.getUserMobileNumber());

        if(verify == false){

            throw new Exception("Otp is Expired.");
        }
        else {

            UserDetails userDetails = new UserDetails();
            userDetails.setUserFirstName(userTemporaryDetails.getUserFirstName());
            userDetails.setUserMiddleName(userTemporaryDetails.getUserMiddleName());
            userDetails.setUserLastName(userTemporaryDetails.getUserLastName());
            userDetails.setKycStatus(true);
            userDetails.setPassword(userTemporaryDetails.getPassword());
            userDetails.setCreatedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
            userDetails.setUserAdhaarNumber(userTemporaryDetails.getUserAdhaarNumber());
            userDetails.setUserCurrentAddress(userTemporaryDetails.getUserCurrentAddress());
            userDetails.setUserPermanentAddress(userTemporaryDetails.getUserPermanentAddress());
            userDetails.setUserMobileNumber(userTemporaryDetails.getUserMobileNumber());
            userDetails.setUserPanNumber(userTemporaryDetails.getUserPanNumber());
            userDetails.setUserImage(userTemporaryDetails.getUserImage());
            userDetails.setUserStatus(true);
            userDetails.setAdhaarImage(userTemporaryDetails.getAdhaarImage());
            userDetails.setPanCardImage(userTemporaryDetails.getPanCardImage());

            userDetailsRepository.saveAndFlush(userDetails);
            return userDetails;
        }
    }


    @Override
    public UserDetails getListByMdn(long userMobileNumber) throws Exception {

        UserDetails userDetails = userDetailsRepository.findByMdn(userMobileNumber);

        if(userDetails == null){
            throw new Exception("User is not available.");
        }
        return userDetails;
    }

    @Override
    public UserDetails update(UserRequest userRequest) {

        UserDetails userDetails = userDetailsRepository.findByMdn(userRequest.getUserMobileNumber());
        userDetails.setUserFirstName(userRequest.getUserFirstName());
        userDetails.setUserMiddleName(userRequest.getUserMiddleName());
        userDetails.setUserLastName(userRequest.getUserLastName());
        userDetails.setUserMobileNumber(userRequest.getUserMobileNumber());
        userDetails.setUserPanNumber(userDetails.getUserPanNumber());
        userDetails.setUserCurrentAddress(userRequest.getUserCurrentAddress());
        userDetails.setUserPermanentAddress(userRequest.getUserPermanentAddress());
        userDetails.setPassword(Base64.getEncoder().encodeToString(userRequest.getPassword().getBytes()));
        userDetails.setUserAdhaarNumber(userRequest.getUserAdhaarNumber());
        userDetails.setUpdatedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));

        userDetailsRepository.saveAndFlush(userDetails);
        return null;
    }

    @Override
    public UserDetails delete(long userMobileNumber) {

        UserDetails userDetails = userDetailsRepository.findByMdn(userMobileNumber);
        userDetails.setUserStatus(false);

        userDetailsRepository.saveAndFlush(userDetails);

        return null;
    }

    @Override
    public UserDetails updateUserImage(UserRequest userRequest) {

        UserDetails userDetails = userDetailsRepository.findByMdn(userRequest.getUserMobileNumber());
        userDetails.setUserImage(Base64.getEncoder().encodeToString(userRequest.getUserImage().getBytes()));

        userDetailsRepository.saveAndFlush(userDetails);
        return null;
    }

    @Override
    public UserDetails updateAdhaarImage(UserRequest userRequest) {
        UserDetails userDetails = userDetailsRepository.findByMdn(userRequest.getUserMobileNumber());
        userDetails.setAdhaarImage(Base64.getEncoder().encodeToString(userRequest.getUserAdhaarImage().getBytes()));

        userDetailsRepository.saveAndFlush(userDetails);
        return null;
    }

    @Override
    public UserDetails updatePanImage(UserRequest userRequest) {
        UserDetails userDetails = userDetailsRepository.findByMdn(userRequest.getUserMobileNumber());
        userDetails.setPanCardImage(Base64.getEncoder().encodeToString(userRequest.getUserPanCardImage().getBytes()));

        userDetailsRepository.saveAndFlush(userDetails);
        return null;
    }

    @Override
    public Page<UserDetails> listAllByPage(Pageable pageable){
        return userDetailsPageRepository.findAll(pageable);
    }
}
