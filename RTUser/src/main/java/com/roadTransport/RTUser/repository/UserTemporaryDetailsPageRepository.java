package com.roadTransport.RTUser.repository;

import com.roadTransport.RTUser.entity.UserTemporaryDetails;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTemporaryDetailsPageRepository extends PagingAndSortingRepository<UserTemporaryDetails,Long> {
}
