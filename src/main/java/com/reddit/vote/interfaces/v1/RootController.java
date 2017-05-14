package com.reddit.vote.interfaces.v1;

import com.reddit.vote.common.URL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RootController {

	@GetMapping(value = "/")
	public ModelAndView redirect() {
		return new ModelAndView("redirect:" + URL.V1_REDDIT_TOPICS);
	}
}
