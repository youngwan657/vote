package com.reddit.vote.repository;

import com.reddit.vote.model.Topic;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.PriorityQueue;

@Repository
public class MaxHeapRepository {
	private PriorityQueue<Topic> topics;
	private static final int DEFAULT_INITIAL_CAPACITY = 11;

	public MaxHeapRepository() {
		topics = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY, (o1, o2) -> (o2.getUp() - o1.getUp()));
	}

	public void save(Collection<? extends Topic> list) {
		topics.addAll(list);
	}

	public void save(Topic topic) {
		topics.offer(topic);
	}

	public Topic poll() {
		return topics.poll();
	}

	public int size() {
		return topics.size();
	}

	public void refresh(Topic topic) {
		topics.remove(topic);
		topics.add(topic);
	}
}
