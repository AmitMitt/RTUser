package com.roadTransport.RTUser.repository;

import com.roadTransport.RTUser.entity.DeletedUserData;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedUserPageRepository extends PagingAndSortingRepository<DeletedUserData, Long> {
}
