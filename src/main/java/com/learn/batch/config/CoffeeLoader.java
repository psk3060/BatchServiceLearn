package com.learn.batch.config;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;

import com.learn.batch.service.model.Coffee;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class CoffeeLoader {
	
	private Logger logger = LoggerFactory.getLogger(CoffeeLoader.class);
	
	private final ReactiveRedisConnectionFactory factory;
	private final ReactiveRedisOperations<String, Coffee> coffeeOps;
	
	public CoffeeLoader(ReactiveRedisConnectionFactory factory, ReactiveRedisOperations<String, Coffee> coffeeOps) {
		this.factory = factory;
		this.coffeeOps = coffeeOps;
		
	}
	
	@PostConstruct
	public void loadData() {
		
		factory
			.getReactiveConnection()
				.serverCommands()
					.flushAll()
						.thenMany(
								Flux
									.just(
											"Jet Black Redis"
											, "Darth Redis"
											, "Black Alert Redis"
									)
									.map(
											name -> new Coffee(UUID.randomUUID().toString(), name)
									)
									.flatMap(
											coffee -> coffeeOps.opsForValue().set(coffee.getId(), coffee)
									)
						)
						.thenMany(
								coffeeOps
									.keys("*")
									.flatMap(coffeeOps.opsForValue()::get)
								
						)
						.subscribe(System.out::println)
						;
	}
}
