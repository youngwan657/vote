package com.reddit.vote.service;

import com.google.common.base.Preconditions;
import com.reddit.vote.model.Topic;
import com.reddit.vote.repository.MaxHeapRepository;
import com.reddit.vote.repository.CacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheService {
	@Autowired
	private MaxHeapRepository maxHeapRepository;

	@Autowired
	private CacheRepository cacheRepository;

	public List<Topic> getTopTopics() {
		return cacheRepository.getTopics();
	}

	public void save(Topic topic) {
		Preconditions.checkNotNull(topic);
		maxHeapRepository.save(topic);
		cacheRepository.refresh();
	}

	public void refresh(Topic topic) {
		Preconditions.checkNotNull(topic);
		maxHeapRepository.refresh(topic);
		cacheRepository.refresh();
	}
}
