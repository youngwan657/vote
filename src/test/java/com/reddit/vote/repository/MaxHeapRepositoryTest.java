package com.reddit.vote.repository;

import com.reddit.vote.model.Topic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MaxHeapRepositoryTest {
	@InjectMocks
	private MaxHeapRepository maxHeapRepository;

	@Test
	public void save() throws Exception {
		// When
		maxHeapRepository.save(new Topic().setId(1));
		maxHeapRepository.save(new Topic().setId(2));

		// Then
		assertThat(maxHeapRepository.size()).isEqualTo(2);
	}

	@Test
	public void saveAll() throws Exception {
		// Given
		List<Topic> topics = new ArrayList<>();
		topics.add(new Topic().setId(1));
		topics.add(new Topic().setId(2));

		// When
		maxHeapRepository.save(topics);

		// Then
		assertThat(maxHeapRepository.size()).isEqualTo(2);
	}

	@Test
	public void poll() throws Exception {
		// Given
		maxHeapRepository.save(new Topic().setId(1));
		maxHeapRepository.save(new Topic().setId(2));

		// When
		maxHeapRepository.poll();

		// Then
		assertThat(maxHeapRepository.size()).isEqualTo(1);
	}

	@Test
	public void refresh() throws Exception {
		// Given
		Topic topic1 = new Topic().setId(1).upvote();
		Topic topic2 = new Topic().setId(1).upvote().upvote();
		maxHeapRepository.save(topic1);
		maxHeapRepository.save(topic2);

		// When
		maxHeapRepository.refresh(topic2);

		// Then
		assertThat(maxHeapRepository.poll()).isEqualTo(topic2);
	}
}
