package com.roadTransport.RTUser.repository;

import com.roadTransport.RTUser.entity.DeletedUserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface DeletedUserRepository extends JpaRepository<DeletedUserData, Long> {

    @Query("Select d from DeletedUserData d where d.userMobileNumber = :userMobileNumber")
    public DeletedUserData findByMdn(@PathVariable("userMobileNumber") Long userMobileNumber);
}
