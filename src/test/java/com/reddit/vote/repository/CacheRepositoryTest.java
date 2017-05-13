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
		cacheRepository.save(new Topic().setId(1).increaseUp().increaseUp().increaseUp());
		cacheRepository.save(new Topic().setId(2).increaseUp());

		// When
		List<Topic> topics = cacheRepository.getTopics();
		Topic topic1 = topics.get(0);
		Topic topic2 = topics.get(1);

		// Then
		assertThat(topic1.getId()).isEqualTo(1);
		assertThat(topic2.getId()).isEqualTo(2);
	}

	@Test
	public void saveReverse() throws Exception {
		// Given
		cacheRepository.save(new Topic().setId(1).increaseUp());
		cacheRepository.save(new Topic().setId(2).increaseUp().increaseUp().increaseUp());

		// When
		List<Topic> topics = cacheRepository.getTopics();
		Topic topic1 = topics.get(0);
		Topic topic2 = topics.get(1);

		// Then
		assertThat(topic1.getId()).isEqualTo(2);
		assertThat(topic2.getId()).isEqualTo(1);
	}
}
