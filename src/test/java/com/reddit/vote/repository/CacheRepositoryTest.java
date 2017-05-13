package com.reddit.vote.repository;

import com.reddit.vote.domain.Topic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CacheRepositoryTest {
	@InjectMocks
	private CacheRepository cacheRepository;

	@Test
	public void save() throws Exception {
		// Given
		cacheRepository.save(new Topic());
		cacheRepository.save(new Topic());

		// When
		List<Topic> topics = cacheRepository.getTopics();

		// Then
		assertThat(topics.size()).isEqualTo(2);
	}
}
