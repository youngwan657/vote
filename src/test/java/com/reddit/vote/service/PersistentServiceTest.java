package com.reddit.vote.service;

import com.reddit.vote.model.Topic;
import com.reddit.vote.repository.PersistentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PersistentServiceTest {
	@InjectMocks
	private PersistentService persistentService;

	@Mock
	private PersistentRepository persistentRepository;

	@Test
	public void vote() throws Exception {
		// Given
		persistentService.save(new Topic().setId(1));

		// When
		persistentService.upvote(1);
		persistentService.downvote(1);

		// Then
		verify(persistentRepository, times(1)).upvote(anyInt());
		verify(persistentRepository, times(1)).downvote(anyInt());
	}
}
