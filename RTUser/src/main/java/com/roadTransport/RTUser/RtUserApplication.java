package com.roadTransport.RTUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RtUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(RtUserApplication.class, args);
	}

}
