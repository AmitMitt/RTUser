package com.roadTransport.RTUser.controller;

import com.roadTransport.RTUser.entity.UserDetails;
import com.roadTransport.RTUser.model.OtpRequest;
import com.roadTransport.RTUser.model.userRequest.UserRequest;
import com.roadTransport.RTUser.model.userResponse.UserResponse;
import com.roadTransport.RTUser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/getlistByPage")
    public Page<UserDetails> getList(Pageable pageable){

        Page<UserDetails> list = userService.listAllByPage(PageRequest.of(0,10, Sort.Direction.ASC));
        return list;
    }

    @GetMapping("/getlist/{userMobileNumber}")
    public ResponseEntity<UserDetails> getListByMdn(@PathVariable("userMobileNumber") long userMobileNumber) throws Exception {

        UserDetails userDetails = userService.getListByMdn(userMobileNumber);
        return ResponseEntity.ok(userDetails);
    }

    @PostMapping("/update")
    public ResponseEntity<UserResponse> update (@RequestBody UserRequest userRequest){

        userService.update(userRequest);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Successfully Updated.");
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/delete/{userMobileNumber}")
    public ResponseEntity<UserResponse> delete (@PathVariable("userMobileNumber") long userMobileNumber){

        userService.delete(userMobileNumber);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Successfully Deleted.");
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/updateUserImage")
    public ResponseEntity<UserResponse> updateUserImage(@RequestBody UserRequest userRequest){

        userService.updateUserImage(userRequest);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Image Updated Successfully.");
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/updateAdhaarImage")
    public ResponseEntity<UserResponse> updateAdhaarImage(@RequestBody UserRequest userRequest){

        userService.updateAdhaarImage(userRequest);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Image Updated Successfully.");
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/updatePanImage")
    public ResponseEntity<UserResponse> updatePanImage(@RequestBody UserRequest userRequest){

        userService.updatePanImage(userRequest);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Image Updated Successfully.");
        return ResponseEntity.ok(userResponse);
    }


}