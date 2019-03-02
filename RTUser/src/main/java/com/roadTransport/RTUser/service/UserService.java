package com.roadTransport.RTUser.service;

import com.roadTransport.RTUser.entity.UserDetails;
import com.roadTransport.RTUser.model.userRequest.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public UserDetails add (UserRequest userRequest) throws Exception;
    public List<UserDetails> getlist() throws Exception;
    public UserDetails getListByMdn(long mdn) throws Exception;
    public UserDetails update(UserRequest userRequest);
    public UserDetails delete(long mdn);
    public UserDetails updateUserImage(UserRequest userRequest);
    public UserDetails updateAdhaarImage(UserRequest userRequest);
    public UserDetails updatePanImage(UserRequest userRequest);

}
