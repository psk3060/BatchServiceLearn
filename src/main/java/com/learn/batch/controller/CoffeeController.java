package com.learn.batch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.batch.service.model.Coffee;

import reactor.core.publisher.Flux;

@RestController
public class CoffeeController {
	
	private Logger logger = LoggerFactory.getLogger(CoffeeController.class);
	
	@Autowired
	private ReactiveRedisOperations<String, Coffee> coffeeOps;
	
	@GetMapping("/coffees")
	public Flux<Coffee> all() {
		
		return coffeeOps
					.keys("*")
					.flatMap(coffeeOps.opsForValue()::get);
							
	}
	
	
}
