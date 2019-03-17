package com.roadTransport.RTUser.service;

import com.roadTransport.RTUser.entity.DeletedUserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface DeletedDetailsService {

    public Page<DeletedUserData> listAllByPage(Pageable pageable);
    public DeletedUserData findByMdn(long userMobileNumber) throws Exception;
}
