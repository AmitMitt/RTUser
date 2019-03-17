package com.roadTransport.RTUser.service;

import com.roadTransport.RTUser.entity.DeletedUserData;
import com.roadTransport.RTUser.entity.UserDetails;;
import com.roadTransport.RTUser.model.OtpRequest;
import com.roadTransport.RTUser.model.userRequest.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public UserDetails add (OtpRequest otpRequest) throws Exception;
    public UserDetails getListByMdn(long mdn) throws Exception;
    public UserDetails update(UserRequest userRequest);
    public DeletedUserData delete(long mdn);
    public UserDetails updateUserImage(UserRequest userRequest);
    public UserDetails updateAdhaarImage(UserRequest userRequest);
    public UserDetails updatePanImage(UserRequest userRequest);
    public Page<UserDetails> listAllByPage(Pageable pageable);
    public UserDetails deleteByOtp(OtpRequest otpRequest) throws Exception;
}
