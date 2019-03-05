package com.roadTransport.RTUser.repository;

import com.roadTransport.RTUser.entity.UserTemporaryDetails;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface UserTemporaryDetailsRepository extends JpaRepository<UserTemporaryDetails, Long> {

    @Query("select u from UserTemporaryDetails u where u.userMobileNumber = :userMobileNumber")
    public UserTemporaryDetails findByMdn(long userMobileNumber);

}