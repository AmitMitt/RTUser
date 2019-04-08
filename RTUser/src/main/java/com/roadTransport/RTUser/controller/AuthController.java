package com.roadTransport.RTUser.controller;

import com.roadTransport.RTUser.entity.*;
import com.roadTransport.RTUser.exception.AppException;
import com.roadTransport.RTUser.model.*;
import com.roadTransport.RTUser.otpService.OtpService;
import com.roadTransport.RTUser.repository.*;
import com.roadTransport.RTUser.security.JwtTokenProvider;
import com.roadTransport.RTUser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    OtpService otpService;

    @Autowired
    MobileNumberRepository mobileNumberRepository;

    @GetMapping("verifyMobile/{mobileNumber}")
    public ResponseEntity<OtpRequest> verify(@PathVariable("mobileNumber") long mobileNumber){

        OtpDetails otpDetails = otpService.getOtp(mobileNumber);
        MobileNumber mobileNumber1 = new MobileNumber();
        mobileNumber1.setMobileNumber(mobileNumber);
        mobileNumber1.setOtpNumber(otpDetails.getOtpNumber());
        mobileNumberRepository.saveAndFlush(mobileNumber1);
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setOtp(otpDetails.getOtpNumber());
        otpRequest.setUserMobileNumber(mobileNumber);
        return  ResponseEntity.ok(otpRequest);
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyOtp(@RequestBody OtpRequest otpRequest){

        boolean response = otpService.verify(otpRequest.getOtp(),otpRequest.getUserMobileNumber());
        MobileNumber mobileNumber = mobileNumberRepository.findMobileNumber(otpRequest.getUserMobileNumber(),otpRequest.getOtp());
        if(response){
            mobileNumber.setStatus(true);
            mobileNumberRepository.saveAndFlush(mobileNumber);
            return ResponseEntity.ok(true);

        }
        else{
            mobileNumber.setStatus(false);
            mobileNumberRepository.saveAndFlush(mobileNumber);
            return ResponseEntity.ok(false);
        }
    }

    @PostMapping("/signin/user")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup/user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByMobile(signUpRequest.getMobile())){
            return new ResponseEntity(new ApiResponse(false, "Mobile already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getMobile());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        userService.add(signUpRequest);

        long pin = Long.parseLong(signUpRequest.getMobile()) % 10000;

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully and wallet pin is: "+pin+ "  Please update it."));
    }
}
