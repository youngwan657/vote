package com.reddit.vote.interfaces.v1;

import com.reddit.vote.common.URL;
import com.reddit.vote.model.Topic;
import com.reddit.vote.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TopicController {

	@Autowired
	private TopicService topicService;

	@GetMapping(value = URL.V1_REDDIT_TOPICS)
	public ModelAndView getTopTopics() {
		List<Topic> topics = topicService.getTopTopics();
		return new ModelAndView("topics/list", "topics", topics);
	}

	@GetMapping(value = URL.V1_REDDIT_TOPICS_NEW)
	public ModelAndView showForm(@ModelAttribute Topic topic) {
		return new ModelAndView("topics/form");
	}

	@PostMapping(value = URL.V1_REDDIT_TOPICS)
	public ModelAndView submit(@Valid Topic topic, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("topics/form", "formErrors", result.getAllErrors());
		}
		topicService.save(topic);
		return new ModelAndView("redirect:" + URL.V1_REDDIT_TOPICS);
	}
}
