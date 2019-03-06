package com.roadTransport.RTUser.controller;

import com.roadTransport.RTUser.entity.UserTemporaryDetails;
import com.roadTransport.RTUser.model.userRequest.UserRequest;
import com.roadTransport.RTUser.model.userResponse.UserResponse;
import com.roadTransport.RTUser.service.UserTemporaryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userTemporary")
public class UserTemporaryDetailsController {

    @Autowired
    private UserTemporaryDetailsService userTemporaryDetailsService;

    @PostMapping("/add")
    public ResponseEntity<UserResponse> add(@RequestBody UserRequest userRequest) throws Exception {

       UserTemporaryDetails userTemporaryDetails = userTemporaryDetailsService.add(userRequest);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("please enter the otp for verification.");
        userResponse.setOtp(userTemporaryDetails.getOtp());
        return ResponseEntity.ok(userResponse);
    }

    @Cacheable(value = "UserTemporaryDetails", key = "#id", unless = "#result.shares < 500")
    @GetMapping("/getData/{userMobileNumber}")
    public ResponseEntity<UserTemporaryDetails> getlistByMdn(@PathVariable("userMobileNumber") long userMobileNumber) throws Exception {

        UserTemporaryDetails userTemporaryDetails = userTemporaryDetailsService.getListByMdn(userMobileNumber);
        return ResponseEntity.ok(userTemporaryDetails);
    }

    @Cacheable(value = "UserTemporaryDetails", key = "#id", unless = "#result.shares < 500")
    @GetMapping("/getlistByPage")
    public Page<UserTemporaryDetails> getList(Pageable pageable){

        Page<UserTemporaryDetails> list = userTemporaryDetailsService.listAllByPage(pageable);
        return list;
    }
}