package com.reddit.vote.service;

import com.reddit.vote.model.Topic;
import com.reddit.vote.model.Vote;
import com.reddit.vote.repository.PersistentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistentService {
	@Autowired
	private PersistentRepository persistentRepository;

	public int size() {
		return persistentRepository.size();
	}

	public void save(Topic topic) {
		persistentRepository.save(topic);
	}

	public Topic vote(Vote vote) {
		Topic topic = null;
		switch(vote.getVoteType()) {
			case UP:
				topic = persistentRepository.upvote(vote.getTopicId());
				break;

			case DOWN:
				topic = persistentRepository.downvote(vote.getTopicId());
				break;
		}
		return topic;
	}
}
