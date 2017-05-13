package com.reddit.vote.repository;

import com.reddit.vote.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TopTopicsRepository {
	@Autowired
	private AllTopicsRepository allTopicsRepository;

	private List<Topic> topics = new ArrayList<>();

	public List<Topic> getTopics() {
		return topics;
	}

	public void save(Topic topic) {
		topics.add(topic);
		refresh();
	}

	public void refresh() {
		final int LIMIT = 20;

		topics.clear();
		for (int i = 0; i < LIMIT && 0 < allTopicsRepository.size(); i++) {
			topics.add(allTopicsRepository.poll());
		}

		allTopicsRepository.addAll(topics);
	}
}
