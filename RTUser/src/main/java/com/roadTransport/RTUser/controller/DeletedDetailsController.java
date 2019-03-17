package com.roadTransport.RTUser.controller;

import com.roadTransport.RTUser.entity.DeletedUserData;
import com.roadTransport.RTUser.service.DeletedDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DeletedData")
public class DeletedDetailsController {

    @Autowired
    private DeletedDetailsService deletedDetailsService;

    @Cacheable(value = "DeletedUser", key = "#userMobileNumber")
    @GetMapping("/getDeletedData/{userMobileNumber}")
    public ResponseEntity<DeletedUserData> getByMdn(@PathVariable("userMobileNumber") Long userMobileNumber) throws Exception {

        DeletedUserData deletedUserData = deletedDetailsService.findByMdn(userMobileNumber);
        return ResponseEntity.ok(deletedUserData);
    }

    @Cacheable(value = "DeletedUser", key = "#userMobileNumber")
    @GetMapping("/getListOfDeletedData")
    public Page<DeletedUserData> getData(Pageable pageable){

        Page<DeletedUserData> list = deletedDetailsService.listAllByPage(PageRequest.of(0, 10, Sort.Direction.ASC));
        return list;
    }
}
