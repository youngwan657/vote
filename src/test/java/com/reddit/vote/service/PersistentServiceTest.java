package com.reddit.vote.service;

import com.reddit.vote.model.Vote;
import com.reddit.vote.model.VoteType;
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
		Vote upvote = new Vote().setVoteType(VoteType.UP);
		Vote downvote = new Vote().setVoteType(VoteType.DOWN);

		// When
		persistentService.vote(upvote);
		persistentService.vote(downvote);

		// Then
		verify(persistentRepository, times(1)).upvote(anyInt());
		verify(persistentRepository, times(1)).downvote(anyInt());
	}
}
