package com.reddit.vote.service;

import com.reddit.vote.model.Topic;
import com.reddit.vote.model.Vote;
import com.reddit.vote.model.VoteType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
	@Autowired
	private CounterService counterService;

	@Autowired
	private CacheService cacheService;

	@Autowired
	private PersistentService persistentService;

	public List<Topic> getTopTopics() {
		return cacheService.getTopTopics();
	}

	public synchronized void save(Topic topic) {
		topic.setId(counterService.generateId());
		persistentService.save(topic);
		cacheService.save(topic);
	}

	public synchronized void vote(Vote vote) {
		Topic topic = persistentService.vote(vote);
		if (vote.getVoteType() == VoteType.UP) {
			cacheService.refresh(topic);
		}
	}
}
