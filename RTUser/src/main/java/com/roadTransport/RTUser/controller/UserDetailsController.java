package com.roadTransport.RTUser.controller;

import com.roadTransport.RTUser.entity.DeletedUserData;
import com.roadTransport.RTUser.entity.UserDetails;
import com.roadTransport.RTUser.model.OtpRequest;
import com.roadTransport.RTUser.model.userRequest.UserRequest;
import com.roadTransport.RTUser.model.userResponse.UserResponse;
import com.roadTransport.RTUser.service.UserService;
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

    @PostMapping("/addUser")
    public ResponseEntity<UserResponse> addUser(@RequestBody OtpRequest otpRequest) throws Exception {

        userService.add(otpRequest);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("User Add Successfully.");
        userResponse.setOtp(otpRequest.getOtp());
        return ResponseEntity.ok(userResponse);
    }

    @Cacheable(value = "UserDetails", key = "#userMobileNumber")
    @GetMapping("/getlistByPage")
    public Page<UserDetails> getList(Pageable pageable) {

        Page<UserDetails> list = userService.listAllByPage(PageRequest.of(0, 10, Sort.Direction.ASC));
        return list;
    }

    @Cacheable(value = "UserDetails", key = "#userMobileNumber")
    @GetMapping("/getlist/{userMobileNumber}")
    public ResponseEntity<UserDetails> getListByMdn(@PathVariable("userMobileNumber") long userMobileNumber) throws Exception {

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


    @DeleteMapping("/delete")
    public ResponseEntity<UserResponse> delete(@RequestBody OtpRequest otpRequest) {

        DeletedUserData deletedUserData = userService.delete(otpRequest.getUserMobileNumber());
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Enter Otp for Verification.");
        userResponse.setOtp(deletedUserData.getOtp());
        return ResponseEntity.ok(userResponse);
    }

    @CacheEvict(value = "UserDetails")
    @PostMapping("/verifyDeletionOtp")
    public ResponseEntity<UserResponse> verifyDeletion(@RequestBody OtpRequest otpRequest) throws Exception {

        userService.deleteByOtp(otpRequest);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("User Successfully Deleted.");
        userResponse.setOtp(otpRequest.getOtp());
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
}