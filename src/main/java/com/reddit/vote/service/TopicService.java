package com.reddit.vote.service;

import com.reddit.vote.domain.Topic;
import com.reddit.vote.domain.Vote;
import com.reddit.vote.repository.CacheRepository;
import com.reddit.vote.repository.PersistentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
	@Autowired
	private CounterService counterService;

	@Autowired
	private CacheRepository cacheRepository;

	@Autowired
	private PersistentRepository persistentRepository;

	public List<Topic> getTopics() {
		return cacheRepository.getTopics();
	}

	public void saveTopic(Topic topic) {
		topic.setId(counterService.generate());
		persistentRepository.save(topic);
		cacheRepository.save(topic);
	}

	public void handleVote(Vote vote) {
		persistentRepository.handleUpDown(vote);
	}
}
