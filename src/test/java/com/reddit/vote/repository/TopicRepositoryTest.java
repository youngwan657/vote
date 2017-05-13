package com.reddit.vote.repository;

import com.reddit.vote.domain.Topic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TopicRepositoryTest {
	@InjectMocks
	private TopicRepository topicRepository;

	@Test
	public void addTopic() throws Exception {
		// Given
		topicRepository.addTopic(new Topic());
		topicRepository.addTopic(new Topic());

		// When
		List<Topic> topics = topicRepository.getTopics();

		// Then
		assertThat(topics.size()).isEqualTo(2);
	}

	@Test
	public void upvotes() throws Exception {
		// Given
		topicRepository.addTopic(new Topic().id(1));
		topicRepository.addTopic(new Topic().id(2));

		// When
		topicRepository.increaseUp(1);
		topicRepository.increaseUp(1);

		// Then
		assertThat(topicRepository.getTopics().get(0).getUp()).isEqualTo(2);
		assertThat(topicRepository.getTopics().get(1).getUp()).isEqualTo(0);
	}

	@Test
	public void downvotes() throws Exception {
		// Given
		topicRepository.addTopic(new Topic().id(1));
		topicRepository.addTopic(new Topic().id(2));

		// When
		topicRepository.increaseDown(2);
		topicRepository.increaseDown(2);

		// Then
		assertThat(topicRepository.getTopics().get(0).getDown()).isEqualTo(0);
		assertThat(topicRepository.getTopics().get(1).getDown()).isEqualTo(2);
	}
}
