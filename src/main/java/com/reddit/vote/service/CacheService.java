package com.reddit.vote.service;

import com.reddit.vote.model.Topic;
import com.reddit.vote.repository.AllTopicsRepository;
import com.reddit.vote.repository.TopTopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheService {
	@Autowired
	private AllTopicsRepository allTopicsRepository;

	@Autowired
	private TopTopicsRepository topTopicsRepository;

	public List<Topic> getTopTopics() {
		return topTopicsRepository.getTopics();
	}

	public void save(Topic topic) {
		allTopicsRepository.save(topic);
		topTopicsRepository.refresh();
	}

	public void refresh(Topic topic) {
		allTopicsRepository.refresh(topic);
		topTopicsRepository.refresh();
	}
}
