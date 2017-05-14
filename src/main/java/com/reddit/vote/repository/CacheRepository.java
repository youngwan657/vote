package com.reddit.vote.repository;

import com.reddit.vote.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CacheRepository {
	@Autowired
	private MaxHeapRepository maxHeapRepository;

	private List<Topic> topics = new ArrayList<>();

	public List<Topic> getTopics() {
		return topics;
	}

	public void refresh() {
		final int LIMIT = 20;

		topics.clear();
		for (int i = 0; i < LIMIT && 0 < maxHeapRepository.size(); i++) {
			topics.add(maxHeapRepository.poll());
		}

		maxHeapRepository.save(topics);
	}
}
