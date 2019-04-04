package com.roadTransport.RTUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.TimeZone;

@EnableCaching
@EnableFeignClients
@SpringBootApplication
@EntityScan(basePackageClasses = {
		RtUserApplication.class,
		Jsr310JpaConverters.class
})
public class RtUserApplication extends CachingConfigurerSupport {

//	@Bean
//	@Override
//	public CacheManager cacheManager() {
//		// configure and return an implementation of Spring's CacheManager SPI
//		SimpleCacheManager cacheManager = new SimpleCacheManager();
//		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("UserDetails")));
//		return cacheManager;
//	}

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(RtUserApplication.class, args);
	}

}
