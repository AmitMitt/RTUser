package com.roadTransport.RTUser.repository;

import com.roadTransport.RTUser.entity.MobileNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface MobileNumberRepository extends JpaRepository<MobileNumber,Long> {

    @Query("select m from MobileNumber m where m.mobileNumber = :mobileNumber and m.otpNumber= :otpNumber")
    public  MobileNumber findMobileNumber(@PathVariable("mobileNumber") long mobileNumber, @PathVariable("otpNumber") long otpNumber);
}
