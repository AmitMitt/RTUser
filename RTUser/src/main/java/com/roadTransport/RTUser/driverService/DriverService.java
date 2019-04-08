package com.roadTransport.RTUser.driverService;

import com.roadTransport.RTUser.model.OtpRequest;
import com.roadTransport.RTUser.model.SignUpRequest;
import com.roadTransport.RTUser.model.userResponse.UserResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Driver", url = "http://localhost:8010/DriverDetails/")
public interface DriverService {

    @PostMapping("/addDriver")
    @Headers("Content-Type: application/json")
    public ResponseEntity<UserResponse> addDriver(@RequestBody SignUpRequest signUpRequest);

    @DeleteMapping("/delete")
    public ResponseEntity<UserResponse> delete(@RequestBody OtpRequest otpRequest);

}
