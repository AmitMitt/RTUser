package com.roadTransport.RTUser.repository;

import com.roadTransport.RTUser.entity.UserTemporaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserTemporaryDetailsRepository extends JpaRepository<UserTemporaryDetails, Long> {

    public UserTemporaryDetails findByMdn(long userMobileNumber);
}