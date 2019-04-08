package com.roadTransport.RTUser.repository;

import com.roadTransport.RTUser.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByEmail(String email);

    Optional<Driver> findByUsernameOrEmail(String username, String email);

    List<Driver> findByIdIn(List<Long> userIds);

    Optional<Driver> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByMobile(String mobile);

    @Query("Select u from Driver u where u.mobile = :mobile")
    public Driver findByMobile(@PathVariable("mobile") String mobile);
}
