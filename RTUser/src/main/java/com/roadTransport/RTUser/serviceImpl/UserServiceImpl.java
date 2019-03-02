package com.roadTransport.RTUser.serviceImpl;

import com.roadTransport.RTUser.entity.UserDetails;
import com.roadTransport.RTUser.entity.UserTemporaryDetails;
import com.roadTransport.RTUser.model.userRequest.UserRequest;
import com.roadTransport.RTUser.otpService.OtpService;
import com.roadTransport.RTUser.repository.UserDetailsRepository;
import com.roadTransport.RTUser.repository.UserTemporaryDetailsRepository;
import com.roadTransport.RTUser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserTemporaryDetailsRepository userTemporaryDetailsRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails add(UserRequest userRequest) throws Exception {
        UserTemporaryDetails userTemporaryDetails = userTemporaryDetailsRepository.findByMdn(userRequest.getUserMobileNumber());
        boolean verify = otpService.verify(userTemporaryDetails.getOtp(),userTemporaryDetails.getUserMobileNumber());

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
    public List<UserDetails> getlist() throws Exception {

        List<UserDetails> list = userDetailsRepository.findAll();

        if(list == null){
            throw new Exception("Empty List.");
        }
        return list;
    }

    @Override
    public UserDetails getListByMdn(long mdn) throws Exception {

        UserDetails userDetails = userDetailsRepository.findByMdn(mdn);

        if(userDetails == null){
            throw new Exception("Data is not available.");
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
        return userDetails;
    }

    @Override
    public UserDetails delete(long mdn) {

        UserDetails userDetails = userDetailsRepository.findByMdn(mdn);
        userDetails.setUserStatus(false);

        userDetailsRepository.saveAndFlush(userDetails);

        return userDetails;
    }

    @Override
    public UserDetails updateUserImage(UserRequest userRequest) {

        UserDetails userDetails = userDetailsRepository.findByMdn(userRequest.getUserMobileNumber());
        userDetails.setUserImage(Base64.getEncoder().encodeToString(userRequest.getUserImage().getBytes()));

        userDetailsRepository.saveAndFlush(userDetails);
        return userDetails;
    }

    @Override
    public UserDetails updateAdhaarImage(UserRequest userRequest) {
        UserDetails userDetails = userDetailsRepository.findByMdn(userRequest.getUserMobileNumber());
        userDetails.setAdhaarImage(Base64.getEncoder().encodeToString(userRequest.getUserAdhaarImage().getBytes()));

        userDetailsRepository.saveAndFlush(userDetails);
        return userDetails;
    }

    @Override
    public UserDetails updatePanImage(UserRequest userRequest) {
        UserDetails userDetails = userDetailsRepository.findByMdn(userRequest.getUserMobileNumber());
        userDetails.setPanCardImage(Base64.getEncoder().encodeToString(userRequest.getUserPanCardImage().getBytes()));

        userDetailsRepository.saveAndFlush(userDetails);
        return userDetails;
    }
}
