package com.reddit.vote.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CounterServiceTest {
	@InjectMocks
	private CounterService counterService;

	@Test
	public void generateId() throws Exception {
		// When
		int first = counterService.generateId();
		int second = counterService.generateId();
		int third = counterService.generateId();

		// Then
		assertThat(first).isEqualTo(1);
		assertThat(second).isEqualTo(2);
		assertThat(third).isEqualTo(3);
	}
}
