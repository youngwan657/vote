package com.reddit.vote.interfaces.v1;

import com.reddit.vote.common.Constants;
import com.reddit.vote.model.Vote;
import com.reddit.vote.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class VoteController {

	@Autowired
	private TopicService topicService;

	@PostMapping(value = Constants.VOTE_URL)
	public ModelAndView vote(@Valid Vote vote) {
		topicService.vote(vote);
		return new ModelAndView("redirect:" + Constants.TOP20_URL);
	}
}
