package com.reddit.vote.repository;

import com.reddit.vote.model.Topic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PersistentRepositoryTest {
	@InjectMocks
	private PersistentRepository persistentRepository;

	@Before
	public void setup() {
		persistentRepository.save(new Topic().setId(1));
		persistentRepository.save(new Topic().setId(2));
	}

	@Test
	public void save() throws Exception {
		// When
		Map<Integer, Topic> topics = persistentRepository.getTopics();

		// Then
		assertThat(topics.size()).isEqualTo(2);
	}

	@Test
	public void upvotes() throws Exception {
		// When
		persistentRepository.upvote(1);
		persistentRepository.upvote(1);

		// Then
		assertThat(persistentRepository.getTopics().get(1).getUp()).isEqualTo(2);
		assertThat(persistentRepository.getTopics().get(2).getUp()).isEqualTo(0);
	}

	@Test
	public void downvotes() throws Exception {
		// When
		persistentRepository.downvote(1);
		persistentRepository.downvote(1);

		// Then
		assertThat(persistentRepository.getTopics().get(1).getDown()).isEqualTo(2);
		assertThat(persistentRepository.getTopics().get(2).getDown()).isEqualTo(0);
	}
}
