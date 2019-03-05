package com.roadTransport.RTUser.repository;

import com.roadTransport.RTUser.entity.UserDetails;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    @Query("select u from UserDetails u where u.userMobileNumber = :userMobileNumber and u.userStatus=true")
    public UserDetails findByMdn(@PathVariable("userMobileNumber") Long userMobileNumber);
}
