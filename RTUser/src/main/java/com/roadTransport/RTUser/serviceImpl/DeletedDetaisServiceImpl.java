package com.roadTransport.RTUser.serviceImpl;

import com.roadTransport.RTUser.entity.DeletedUserData;
import com.roadTransport.RTUser.repository.DeletedUserPageRepository;
import com.roadTransport.RTUser.repository.DeletedUserRepository;
import com.roadTransport.RTUser.service.DeletedDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeletedDetaisServiceImpl implements DeletedDetailsService {

    @Autowired
    private DeletedUserRepository deletedUserRepository;

    @Autowired
    private DeletedUserPageRepository deletedUserPageRepository;

    @Override
    public Page<DeletedUserData> listAllByPage(Pageable pageable) {
        return deletedUserPageRepository.findAll(pageable);
    }

    @Override
    public DeletedUserData findByMdn(long userMobileNumber) throws Exception {

        DeletedUserData deletedUserData = deletedUserRepository.findByMdn(userMobileNumber);
        if(deletedUserData == null){
            throw new Exception("Data Not Available.");
        }
        return deletedUserData;
    }
}
