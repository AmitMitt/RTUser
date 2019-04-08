package com.roadTransport.RTUser.repository;

import com.roadTransport.RTUser.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEmail(String email);

    Optional<Admin> findByUsernameOrEmail(String username, String email);

    List<Admin> findByIdIn(List<Long> userIds);

    Optional<Admin> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByMobile(String mobile);

    @Query("Select u from Admin u where u.mobile = :mobile")
    public Admin findByMobile(@PathVariable("mobile") String mobile);

}
