package com.reddit.vote.repository;

import com.reddit.vote.domain.Topic;
import com.reddit.vote.domain.UpDown;
import com.reddit.vote.domain.Vote;
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
		topicRepository.addTopic(new Topic().setId(1));
		topicRepository.addTopic(new Topic().setId(2));

		// When
		topicRepository.handleUpDown(new Vote().setTopicId(1).setUpDown(UpDown.UP));
		topicRepository.handleUpDown(new Vote().setTopicId(1).setUpDown(UpDown.UP));

		// Then
		assertThat(topicRepository.getTopics().get(0).getUp()).isEqualTo(2);
		assertThat(topicRepository.getTopics().get(1).getUp()).isEqualTo(0);
	}

	@Test
	public void downvotes() throws Exception {
		// Given
		topicRepository.addTopic(new Topic().setId(1));
		topicRepository.addTopic(new Topic().setId(2));

		// When
		topicRepository.handleUpDown(new Vote().setTopicId(2).setUpDown(UpDown.DOWN));
		topicRepository.handleUpDown(new Vote().setTopicId(2).setUpDown(UpDown.DOWN));

		// Then
		assertThat(topicRepository.getTopics().get(0).getDown()).isEqualTo(0);
		assertThat(topicRepository.getTopics().get(1).getDown()).isEqualTo(2);
	}
}
