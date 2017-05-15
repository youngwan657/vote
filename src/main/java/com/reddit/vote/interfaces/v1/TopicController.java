package com.reddit.vote.interfaces.v1;

import com.google.common.base.Preconditions;
import com.reddit.vote.common.QueryString;
import com.reddit.vote.common.Uri;
import com.reddit.vote.model.Topic;
import com.reddit.vote.service.TopicService;
import com.reddit.vote.support.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class TopicController {

	@Autowired
	private TopicService topicService;

	@GetMapping(value = Uri.V1_REDDIT_TOPICS)
	public ModelAndView getTopTopics(@RequestParam Map<String, String> param) {
		Preconditions.checkArgument(Validator.validateTop20(param));

		List<Topic> topics = topicService.getTopTopics();
		return new ModelAndView("topics/list", "topics", topics);
	}

	@GetMapping(value = Uri.V1_REDDIT_TOPICS_NEW)
	public ModelAndView showForm(@ModelAttribute Topic topic) {
		return new ModelAndView("topics/form");
	}

	@PostMapping(value = Uri.V1_REDDIT_TOPICS)
	public ModelAndView submit(@Valid Topic topic, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("topics/form", "formErrors", result.getAllErrors());
		}
		topicService.save(topic);
		return new ModelAndView("redirect:" + Uri.V1_REDDIT_TOPICS + QueryString.TOP20);
	}
}
