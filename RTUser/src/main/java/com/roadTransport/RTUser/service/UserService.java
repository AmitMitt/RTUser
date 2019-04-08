package com.roadTransport.RTUser.service;

import com.roadTransport.RTUser.entity.UserDetails;;
import com.roadTransport.RTUser.model.OtpRequest;
import com.roadTransport.RTUser.model.SignUpRequest;
import com.roadTransport.RTUser.model.userRequest.PasswordRequest;
import com.roadTransport.RTUser.model.userRequest.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public UserDetails add (SignUpRequest signUpRequest);
    public UserDetails getListByMdn(String mdn) throws Exception;
    public UserDetails update(UserRequest userRequest);
    public UserDetails updateUserImage(UserRequest userRequest);
    public UserDetails updateAdhaarImage(UserRequest userRequest);
    public UserDetails updatePanImage(UserRequest userRequest);
    public Page<UserDetails> listAllByPage(Pageable pageable);
    public void deleteUser(OtpRequest otpRequest) throws Exception;
    public UserDetails updatePassword(PasswordRequest passwordRequest) throws Exception;
}
