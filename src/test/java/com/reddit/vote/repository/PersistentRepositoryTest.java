package com.reddit.vote.repository;

import com.reddit.vote.model.Topic;
import com.reddit.vote.model.VoteType;
import com.reddit.vote.model.Vote;
import com.reddit.vote.service.CacheService;
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
	private CacheService cacheService;

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
		cacheService.save(topic1);
		cacheService.save(topic2);

		// When
		persistentRepository.vote(new Vote().setTopicId(1).setVoteType(VoteType.UP));
		persistentRepository.vote(new Vote().setTopicId(1).setVoteType(VoteType.UP));

		// Then
		assertThat(cacheService.getTopTopics().get(0).getUp()).isEqualTo(2);
		assertThat(cacheService.getTopTopics().get(1).getUp()).isEqualTo(0);
	}

	@Test
	public void downvotes() throws Exception {
		// Given
		Topic topic1 = new Topic().setId(1);
		Topic topic2 = new Topic().setId(2);
		persistentRepository.save(topic1);
		persistentRepository.save(topic2);
		cacheService.save(topic1);
		cacheService.save(topic2);

		// When
		persistentRepository.vote(new Vote().setTopicId(2).setVoteType(VoteType.DOWN));
		persistentRepository.vote(new Vote().setTopicId(2).setVoteType(VoteType.DOWN));

		// Then
		assertThat(cacheService.getTopTopics().get(0).getDown()).isEqualTo(0);
		assertThat(cacheService.getTopTopics().get(1).getDown()).isEqualTo(2);
	}
}
