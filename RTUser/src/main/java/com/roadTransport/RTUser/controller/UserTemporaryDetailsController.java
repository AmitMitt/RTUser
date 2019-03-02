package com.roadTransport.RTUser.controller;

import com.roadTransport.RTUser.entity.UserTemporaryDetails;
import com.roadTransport.RTUser.model.userRequest.UserRequest;
import com.roadTransport.RTUser.model.userResponse.UserResponse;
import com.roadTransport.RTUser.service.UserTemporaryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userTemporary")
public class UserTemporaryDetailsController {

    @Autowired
    private UserTemporaryDetailsService userTemporaryDetailsService;

    @PostMapping("/add")
    public ResponseEntity<UserResponse> add(@RequestBody UserRequest userRequest) throws Exception {

        userTemporaryDetailsService.add(userRequest);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("User Data Add Successfully please enter the otp for verification.");
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/getData/{mdn}")
    public ResponseEntity<UserTemporaryDetails> getlistByMdn(@Param("mdn") long userMobileNumber) throws Exception {

        UserTemporaryDetails userTemporaryDetails = userTemporaryDetailsService.getListByMdn(userMobileNumber);
        return ResponseEntity.ok(userTemporaryDetails);
    }

    @GetMapping("/getDataList")
    public ResponseEntity<List<UserTemporaryDetails>> getList() throws Exception {
        List<UserTemporaryDetails> list = userTemporaryDetailsService.getlist();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getOtp/{mdn}")
    public ResponseEntity<UserResponse> getOtpByMdn(@Param("mdn") long userMobileNumber) throws Exception{

        UserTemporaryDetails userTemporaryDetails = userTemporaryDetailsService.getOtp(userMobileNumber);
        UserResponse userResponse = new UserResponse();
        userResponse.setOtp(userTemporaryDetails.getOtp());
        return ResponseEntity.ok(userResponse);
    }


}
