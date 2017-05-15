package com.reddit.vote.service;

import com.google.common.base.Preconditions;
import com.reddit.vote.model.Topic;
import com.reddit.vote.model.Vote;
import com.reddit.vote.repository.PersistentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistentService {
	@Autowired
	private PersistentRepository persistentRepository;

	public void save(Topic topic) {
		persistentRepository.save(topic);
	}

	public Topic upvote(Integer topicId) {
		return persistentRepository.upvote(topicId);
	}

	public Topic downvote(Integer topicId) {
		return persistentRepository.downvote(topicId);
	}
}
