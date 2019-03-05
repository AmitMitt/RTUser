package com.roadTransport.RTUser.repository;

import com.roadTransport.RTUser.entity.UserDetails;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsPageRepository extends PagingAndSortingRepository<UserDetails,Long> {
}
