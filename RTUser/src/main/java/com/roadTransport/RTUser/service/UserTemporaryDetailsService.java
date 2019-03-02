package com.roadTransport.RTUser.service;

import com.roadTransport.RTUser.entity.UserTemporaryDetails;
import com.roadTransport.RTUser.model.userRequest.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserTemporaryDetailsService {

    public UserTemporaryDetails add (UserRequest userRequest) throws Exception;
    public List<UserTemporaryDetails> getlist() throws Exception;
    public UserTemporaryDetails getListByMdn(long mdn) throws Exception;
    public UserTemporaryDetails getOtp(long mdn) throws Exception;

}
