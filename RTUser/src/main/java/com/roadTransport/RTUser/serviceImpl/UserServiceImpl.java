package com.roadTransport.RTUser.serviceImpl;

import com.roadTransport.RTUser.entity.RoleName;
import com.roadTransport.RTUser.entity.User;
import com.roadTransport.RTUser.entity.UserDetails;
import com.roadTransport.RTUser.model.OtpRequest;
import com.roadTransport.RTUser.model.SignUpRequest;
import com.roadTransport.RTUser.model.userRequest.PasswordRequest;
import com.roadTransport.RTUser.model.userRequest.UserRequest;
import com.roadTransport.RTUser.otpService.OtpService;
import com.roadTransport.RTUser.repository.UserDetailsPageRepository;
import com.roadTransport.RTUser.repository.UserDetailsRepository;
import com.roadTransport.RTUser.repository.UserRepository;
import com.roadTransport.RTUser.service.UserService;
import com.roadTransport.RTUser.walletService.WalletRequest;
import com.roadTransport.RTUser.walletService.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Calendar;
import java.util.TimeZone;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserDetailsPageRepository userDetailsPageRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails add(SignUpRequest signUpRequest) {

        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(signUpRequest.getEmail());
        userDetails.setUserName(signUpRequest.getName());
        userDetails.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userDetails.setUserMobileNumber(signUpRequest.getMobile());
        if(signUpRequest.getRole().equalsIgnoreCase("User")){
        userDetails.setUserRole(String.valueOf(RoleName.ROLE_USER));}
        if(signUpRequest.getRole().equalsIgnoreCase("Admin")){
            userDetails.setUserRole(String.valueOf(RoleName.ROLE_ADMIN));
        }
        userDetails.setKycStatus(false);
        userDetails.setUserStatus(true);
        userDetails.setDeleted(false);
        userDetails.setCreatedDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());

        WalletRequest walletRequest = new WalletRequest();
        walletRequest.setOwnerName(signUpRequest.getName());
        walletRequest.setWalletId(Long.parseLong(signUpRequest.getMobile()));
        long pin = Long.parseLong(signUpRequest.getMobile()) % 10000;
        walletRequest.setWalletPin(String.valueOf(pin));
        walletService.add(walletRequest);

        userDetailsRepository.save(userDetails);

        return userDetails;
    }

    @Override
    public UserDetails getListByMdn(String userMobileNumber) throws Exception {

        UserDetails userDetails = userDetailsRepository.findByMdn(userMobileNumber);

        if(userDetails == null){
            throw new Exception("User is not available.");
        }
        return userDetails;
    }

    @Override
    public UserDetails update(UserRequest userRequest) {

        UserDetails userDetails = userDetailsRepository.findByMdn(userRequest.getUserMobileNumber());
        userDetails.setUserPanNumber(userDetails.getUserPanNumber());
        userDetails.setUserCurrentAddress(userRequest.getUserCurrentAddress());
        userDetails.setUserPermanentAddress(userRequest.getUserPermanentAddress());
        userDetails.setUserAdhaarNumber(userRequest.getUserAdhaarNumber());
        userDetails.setUpdatedDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
        userDetails.setDob(userRequest.getDob());
        userDetails.setDeleted(false);

        userDetailsRepository.saveAndFlush(userDetails);
        return null;
    }

    @Override
    public UserDetails deleteByOtp(OtpRequest otpRequest) throws Exception {

        UserDetails userDetails = userDetailsRepository.findByMdn(String.valueOf(otpRequest.getUserMobileNumber()));

        boolean verify = otpService.verify(otpRequest.getOtp(),otpRequest.getUserMobileNumber());

        User user = userRepository.findByMobile(String.valueOf(otpRequest.getUserMobileNumber()));

        userRepository.delete(user);

        if(verify == false){

            throw new Exception("Otp is Expired.");
        }

        walletService.delete(otpRequest.getUserMobileNumber());
        userDetails.setDeleted(true);
        userDetailsRepository.saveAndFlush(userDetails);

        return null;
    }

    @Override
    public UserDetails updatePassword(PasswordRequest passwordRequest) throws Exception {

        UserDetails userDetails = userDetailsRepository.findByMdn(passwordRequest.getUserMobileNumber());

        if(passwordEncoder.encode(passwordRequest.getCurrentPassword()).equalsIgnoreCase(userDetails.getPassword())){
            if(passwordRequest.getNewPassword().equalsIgnoreCase(passwordRequest.getConfirmPassword())){
                userDetails.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
                userDetailsRepository.saveAndFlush(userDetails);
            }
            else {
                throw new Exception("New Password is not match with confirm Password.");
            }
        }
        else {
            throw new Exception("Current Password is not Match.");
        }

        return userDetails;
    }

    @Override
    public UserDetails updateUserImage(UserRequest userRequest) {

        UserDetails userDetails = userDetailsRepository.findByMdn(userRequest.getUserMobileNumber());
        userDetails.setUserImage(Base64.getEncoder().encodeToString(userRequest.getUserImage().getBytes()));
        userDetails.setUpdatedDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
        userDetailsRepository.saveAndFlush(userDetails);
        return null;
    }

    @Override
    public UserDetails updateAdhaarImage(UserRequest userRequest) {
        UserDetails userDetails = userDetailsRepository.findByMdn(userRequest.getUserMobileNumber());
        userDetails.setAdhaarImage(Base64.getEncoder().encodeToString(userRequest.getUserAdhaarImage().getBytes()));
        userDetails.setUpdatedDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
        userDetailsRepository.saveAndFlush(userDetails);
        return null;
    }

    @Override
    public UserDetails updatePanImage(UserRequest userRequest) {
        UserDetails userDetails = userDetailsRepository.findByMdn(userRequest.getUserMobileNumber());
        userDetails.setPanCardImage(Base64.getEncoder().encodeToString(userRequest.getUserPanCardImage().getBytes()));
        userDetails.setUpdatedDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
        userDetailsRepository.saveAndFlush(userDetails);
        return null;
    }

    @Override
    public Page<UserDetails> listAllByPage(Pageable pageable){
        return userDetailsPageRepository.findAll(pageable);
    }
}
