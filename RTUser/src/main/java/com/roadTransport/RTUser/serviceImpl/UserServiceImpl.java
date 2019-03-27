package com.roadTransport.RTUser.serviceImpl;

import com.roadTransport.RTUser.entity.DeletedUserData;
import com.roadTransport.RTUser.entity.UserDetails;
import com.roadTransport.RTUser.entity.UserTemporaryDetails;
import com.roadTransport.RTUser.model.OtpDetails;
import com.roadTransport.RTUser.model.OtpRequest;
import com.roadTransport.RTUser.model.userRequest.PasswordRequest;
import com.roadTransport.RTUser.model.userRequest.UserRequest;
import com.roadTransport.RTUser.otpService.OtpService;
import com.roadTransport.RTUser.repository.DeletedUserRepository;
import com.roadTransport.RTUser.repository.UserDetailsPageRepository;
import com.roadTransport.RTUser.repository.UserDetailsRepository;
import com.roadTransport.RTUser.repository.UserTemporaryDetailsRepository;
import com.roadTransport.RTUser.service.UserService;
import com.roadTransport.RTUser.walletService.WalletRequest;
import com.roadTransport.RTUser.walletService.WalletService;
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

    @Autowired
    private DeletedUserRepository deletedUserRepository;

    @Autowired
    private WalletService walletService;

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
            userDetails.setDob(userTemporaryDetails.getDob());

            WalletRequest walletRequest = new WalletRequest();
            walletRequest.setOwnerName(userTemporaryDetails.getUserFirstName());
            walletRequest.setWalletId(userTemporaryDetails.getUserMobileNumber());
            walletRequest.setWalletPin(String.valueOf(userTemporaryDetails.getUserMobileNumber() % 10000));
            walletService.add(walletRequest);
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
        userDetails.setUserAdhaarNumber(userRequest.getUserAdhaarNumber());
        userDetails.setUpdatedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
        userDetails.setDob(userRequest.getDob());

        WalletRequest walletRequest = new WalletRequest();
        walletRequest.setOwnerName(userRequest.getUserFirstName());
        walletService.update(walletRequest);

        userDetailsRepository.saveAndFlush(userDetails);
        return null;
    }

    @Override
    public DeletedUserData delete(long userMobileNumber) {

        UserDetails userDetails = userDetailsRepository.findByMdn(userMobileNumber);
        DeletedUserData deletedUserData = new DeletedUserData();
        deletedUserData.setAdhaarImage(userDetails.getAdhaarImage());
        deletedUserData.setCreatedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
        deletedUserData.setDob(userDetails.getDob());
        deletedUserData.setPanCardImage(userDetails.getPanCardImage());
        deletedUserData.setUserCurrentAddress(userDetails.getUserCurrentAddress());
        deletedUserData.setPassword(userDetails.getPassword());
        deletedUserData.setUserAdhaarNumber(userDetails.getUserAdhaarNumber());
        deletedUserData.setUserFirstName(userDetails.getUserFirstName());
        deletedUserData.setUserImage(userDetails.getUserImage());
        deletedUserData.setUserLastName(userDetails.getUserLastName());
        deletedUserData.setUserMiddleName(userDetails.getUserMiddleName());
        deletedUserData.setUserMobileNumber(userDetails.getUserMobileNumber());
        deletedUserData.setUserPanNumber(userDetails.getUserPanNumber());
        deletedUserData.setUserPermanentAddress(userDetails.getUserPermanentAddress());
        deletedUserData.setUserStatus(false);

        OtpDetails otpDetails = otpService.getOtp(userMobileNumber);
        deletedUserData.setOtp(otpDetails.getOtpNumber());

        deletedUserRepository.saveAndFlush(deletedUserData);

        return deletedUserData;
    }

    @Override
    public UserDetails deleteByOtp(OtpRequest otpRequest) throws Exception {

        UserDetails userDetails = userDetailsRepository.findByMdn(otpRequest.getUserMobileNumber());

        boolean verify = otpService.verify(otpRequest.getOtp(),otpRequest.getUserMobileNumber());

        if(verify == false){

            throw new Exception("Otp is Expired.");
        }
        walletService.delete(otpRequest.getUserMobileNumber());
        userDetailsRepository.delete(userDetails);
        return null;
    }

    @Override
    public UserDetails updatePassword(PasswordRequest passwordRequest) throws Exception {

        UserDetails userDetails = userDetailsRepository.findByMdn(passwordRequest.getUserMobileNumber());

        byte[] password = Base64.getDecoder().decode(userDetails.getPassword());
        String decodedString = new String(password);

        if(passwordRequest.getCurrentPassword().equalsIgnoreCase(decodedString)){
            if(passwordRequest.getNewPassword().equalsIgnoreCase(passwordRequest.getConfirmPassword())){
                userDetails.setPassword(Base64.getEncoder().encodeToString(passwordRequest.getNewPassword().getBytes()));
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
        userDetails.setUpdatedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
        userDetailsRepository.saveAndFlush(userDetails);
        return null;
    }

    @Override
    public UserDetails updateAdhaarImage(UserRequest userRequest) {
        UserDetails userDetails = userDetailsRepository.findByMdn(userRequest.getUserMobileNumber());
        userDetails.setAdhaarImage(Base64.getEncoder().encodeToString(userRequest.getUserAdhaarImage().getBytes()));
        userDetails.setUpdatedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
        userDetailsRepository.saveAndFlush(userDetails);
        return null;
    }

    @Override
    public UserDetails updatePanImage(UserRequest userRequest) {
        UserDetails userDetails = userDetailsRepository.findByMdn(userRequest.getUserMobileNumber());
        userDetails.setPanCardImage(Base64.getEncoder().encodeToString(userRequest.getUserPanCardImage().getBytes()));
        userDetails.setUpdatedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
        userDetailsRepository.saveAndFlush(userDetails);
        return null;
    }

    @Override
    public Page<UserDetails> listAllByPage(Pageable pageable){
        return userDetailsPageRepository.findAll(pageable);
    }
}
