package com.reddit.vote.repository;

import com.reddit.vote.domain.Topic;
import com.reddit.vote.domain.UpDown;
import com.reddit.vote.domain.Vote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PersistentRepositoryTest {
	@InjectMocks
	private PersistentRepository persistentRepository;

	@InjectMocks
	private CacheRepository cacheRepository;

	@Test
	public void save() throws Exception {
		// Given
		persistentRepository.save(new Topic().setId(1));
		persistentRepository.save(new Topic().setId(2));

		// When
		int size = persistentRepository.size();

		// Then
		assertThat(size).isEqualTo(2);
	}

	@Test
	public void upvotes() throws Exception {
		// Given
		Topic topic1 = new Topic().setId(1);
		Topic topic2 = new Topic().setId(2);
		persistentRepository.save(topic1);
		persistentRepository.save(topic2);
		cacheRepository.save(topic1);
		cacheRepository.save(topic2);

		// When
		persistentRepository.handleUpDown(new Vote().setTopicId(1).setUpDown(UpDown.UP));
		persistentRepository.handleUpDown(new Vote().setTopicId(1).setUpDown(UpDown.UP));

		// Then
		assertThat(cacheRepository.getTopics().get(0).getUp()).isEqualTo(2);
		assertThat(cacheRepository.getTopics().get(1).getUp()).isEqualTo(0);
	}

	@Test
	public void downvotes() throws Exception {
		// Given
		Topic topic1 = new Topic().setId(1);
		Topic topic2 = new Topic().setId(2);
		persistentRepository.save(topic1);
		persistentRepository.save(topic2);
		cacheRepository.save(topic1);
		cacheRepository.save(topic2);

		// When
		persistentRepository.handleUpDown(new Vote().setTopicId(2).setUpDown(UpDown.DOWN));
		persistentRepository.handleUpDown(new Vote().setTopicId(2).setUpDown(UpDown.DOWN));

		// Then
		assertThat(cacheRepository.getTopics().get(0).getDown()).isEqualTo(0);
		assertThat(cacheRepository.getTopics().get(1).getDown()).isEqualTo(2);
	}
}
