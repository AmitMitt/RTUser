package com.roadTransport.RTUser.service;

import com.roadTransport.RTUser.entity.UserTemporaryDetails;
import com.roadTransport.RTUser.model.userRequest.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserTemporaryDetailsService {

    public UserTemporaryDetails add (UserRequest userRequest) throws Exception;
    public UserTemporaryDetails getListByMdn(long mdn) throws Exception;
    public UserTemporaryDetails getOtp(long mdn) throws Exception;
    public Page<UserTemporaryDetails> listAllByPage(Pageable pageable);
}
