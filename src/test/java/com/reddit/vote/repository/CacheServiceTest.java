package com.reddit.vote.repository;

import com.reddit.vote.model.Topic;
import com.reddit.vote.service.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CacheServiceTest {
	@InjectMocks
	private CacheService cacheService;

	@Test
	public void save() throws Exception {
		// Given
		cacheService.save(new Topic().setId(1).upvote().upvote().upvote());
		cacheService.save(new Topic().setId(2).upvote());

		// When
		List<Topic> topics = cacheService.getTopTopics();
		Topic topic1 = topics.get(0);
		Topic topic2 = topics.get(1);

		// Then
		assertThat(topic1.getId()).isEqualTo(1);
		assertThat(topic2.getId()).isEqualTo(2);
	}

	@Test
	public void saveReverse() throws Exception {
		// Given
		cacheService.save(new Topic().setId(1).upvote());
		cacheService.save(new Topic().setId(2).upvote().upvote().upvote());

		// When
		List<Topic> topics = cacheService.getTopTopics();
		Topic topic1 = topics.get(0);
		Topic topic2 = topics.get(1);

		// Then
		assertThat(topic1.getId()).isEqualTo(2);
		assertThat(topic2.getId()).isEqualTo(1);
	}
}
