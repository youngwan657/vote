package com.reddit.vote.interfaces.v1;

import com.reddit.vote.common.Constants;
import com.reddit.vote.domain.Topic;
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

	@GetMapping(value = Constants.LIST_URL)
	public ModelAndView getTopics() {
		List<Topic> topics = topicService.getTopics();
		return new ModelAndView("topics/list", "topics", topics);
	}

	@GetMapping(value = Constants.FORM_URL)
	public ModelAndView showForm(@ModelAttribute Topic topic) {
		return new ModelAndView("topics/form");
	}

	@PostMapping(value = Constants.CREATION_URL)
	public ModelAndView submit(@Valid Topic topic, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("topics/form", "formErrors", result.getAllErrors());
		}
		topicService.saveTopic(topic);
		return new ModelAndView("redirect:" + Constants.LIST_URL);
	}
}
