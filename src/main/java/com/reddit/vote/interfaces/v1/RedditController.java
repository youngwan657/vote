package com.reddit.vote.interfaces.v1;

import com.reddit.vote.domain.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RedditController {

	@GetMapping(value = "/v1/reddit/topics")
	public ModelAndView getTopics() {
		List<Topic> topics = new ArrayList<>();
		return new ModelAndView("topics/list", "topics", topics);
	}

	@GetMapping(value = "/v1/reddit/topics/new")
	public ModelAndView createForm(@ModelAttribute Topic topic) {
		return new ModelAndView("topics/form");
	}

	@PostMapping(value = "/v1/reddit/topics")
	public ModelAndView create(@Valid Topic topic, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("topics/form", "formErrors", result.getAllErrors());
		}
		return new ModelAndView("redirect:/v1/reddit/topics");
	}
}
