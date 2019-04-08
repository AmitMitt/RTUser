package com.roadTransport.RTUser.controller;

import com.roadTransport.RTUser.entity.UserDetails;
import com.roadTransport.RTUser.model.OtpRequest;
import com.roadTransport.RTUser.model.userRequest.PasswordRequest;
import com.roadTransport.RTUser.model.userRequest.UserRequest;
import com.roadTransport.RTUser.model.userResponse.UserResponse;
import com.roadTransport.RTUser.service.UserService;
import com.roadTransport.RTUser.walletService.WalletPinRequest;
import com.roadTransport.RTUser.walletService.WalletRequest;
import com.roadTransport.RTUser.walletService.WalletResponse;
import com.roadTransport.RTUser.walletService.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserDetailsController {

    @Autowired
    private UserService userService;

    @Autowired
    private WalletService walletService;

    @Cacheable(value = "UserDetails", key = "#userMobileNumber")
    @GetMapping("/getlistByPage")
    public Page<UserDetails> getList(Pageable pageable) {

        Page<UserDetails> list = userService.listAllByPage(PageRequest.of(0, 10, Sort.Direction.ASC));
        return list;
    }

    @Cacheable(value = "UserDetails", key = "#userMobileNumber")
    @GetMapping("/getlist/{userMobileNumber}")
    public ResponseEntity<UserDetails> getListByMdn(@PathVariable("userMobileNumber") String userMobileNumber) throws Exception {

        UserDetails userDetails = userService.getListByMdn(userMobileNumber);
        return ResponseEntity.ok(userDetails);
    }

    @CachePut(value = "UserDetails", key = "#userMobileNumber")
    @PutMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestBody UserRequest userRequest) {

        userService.update(userRequest);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Successfully Updated.");
        return ResponseEntity.ok(userResponse);
    }

    @CacheEvict(value = "UserDetails")
    @DeleteMapping("/deleteUser")
    public ResponseEntity<UserResponse> deleteUser(@RequestBody OtpRequest otpRequest) throws Exception {

         userService.deleteUser(otpRequest);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Enter Otp for Verification.");
        return ResponseEntity.ok(userResponse);
    }

    @CachePut(value = "UserDetails", key = "#userMobileNumber")
    @PutMapping("/updateUserImage")
    public ResponseEntity<UserResponse> updateUserImage(@RequestBody UserRequest userRequest){

        userService.updateUserImage(userRequest);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Image Updated Successfully.");
        return ResponseEntity.ok(userResponse);
    }

    @CachePut(value = "UserDetails", key = "#userMobileNumber")
    @PutMapping("/updateAdhaarImage")
    public ResponseEntity<UserResponse> updateAdhaarImage(@RequestBody UserRequest userRequest){

        userService.updateAdhaarImage(userRequest);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Image Updated Successfully.");
        return ResponseEntity.ok(userResponse);
    }

    @CachePut(value = "UserDetails", key = "#userMobileNumber")
    @PutMapping("/updatePanImage")
    public ResponseEntity<UserResponse> updatePanImage(@RequestBody UserRequest userRequest){

        userService.updatePanImage(userRequest);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Image Updated Successfully.");
        return ResponseEntity.ok(userResponse);
    }

    @CachePut(value = "UserDetails", key = "#userMobileNumber")
    @PutMapping("/updatePassword")
    public ResponseEntity<UserResponse> updatePassword(@RequestBody PasswordRequest passwordRequest) throws Exception {

        userService.updatePassword(passwordRequest);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Password Change Successfully.");
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/updateWalletPin")
    public ResponseEntity<WalletResponse> updatepin(@RequestBody WalletPinRequest walletPinRequest){
        walletService.updatePin(walletPinRequest);
        WalletResponse walletResponse = new WalletResponse();
        walletResponse.setMessage("Successfully Change.");
        return ResponseEntity.ok(walletResponse);
    }

    @PutMapping("/updateWalletBalance")
    public ResponseEntity<ResponseEntity<WalletResponse>> updateBalance(@RequestBody WalletRequest walletRequest){
        walletService.updateBalance(walletRequest);
        ResponseEntity<WalletResponse> walletResponse = walletService.getBalance(walletRequest.getWalletId(),walletRequest.getRoleName());
        return ResponseEntity.ok(walletResponse);
    }

}