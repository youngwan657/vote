package com.reddit.vote.interfaces.v1;

import com.google.common.base.Preconditions;
import com.reddit.vote.common.QueryString;
import com.reddit.vote.common.Uri;
import com.reddit.vote.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VoteController {

	@Autowired
	private TopicService topicService;

	@PostMapping(value = Uri.V1_REDDIT_TOPICS_ID_UP)
	public ModelAndView upvote(@PathVariable Integer topicId) {
		Preconditions.checkNotNull(topicId);
		topicService.upvote(topicId);
		return new ModelAndView("redirect:" + Uri.V1_REDDIT_TOPICS + QueryString.TOP20);
	}

	@PostMapping(value = Uri.V1_REDDIT_TOPICS_ID_DOWN)
	public ModelAndView downvote(@PathVariable Integer topicId) {
		Preconditions.checkNotNull(topicId);
		topicService.downvote(topicId);
		return new ModelAndView("redirect:" + Uri.V1_REDDIT_TOPICS + QueryString.TOP20);
	}
}
