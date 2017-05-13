package com.reddit.vote.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CounterService {
	private AtomicInteger counter = new AtomicInteger();

	public int generateId() {
		return counter.incrementAndGet();
	}
}
