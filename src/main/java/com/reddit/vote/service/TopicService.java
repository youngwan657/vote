package com.reddit.vote.service;

import com.reddit.vote.model.Topic;
import com.reddit.vote.model.Vote;
import com.reddit.vote.repository.PersistentRepository;
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
	private PersistentRepository persistentRepository;

	public List<Topic> getTopTopics() {
		return cacheService.getTopTopics();
	}

	public void save(Topic topic) {
		topic.setId(counterService.generateId());
		persistentRepository.save(topic);
		cacheService.save(topic);
	}

	public void vote(Vote vote) {
		Topic topic = persistentRepository.vote(vote);
		cacheService.refresh(topic);
	}
}
