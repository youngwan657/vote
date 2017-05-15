package com.reddit.vote.interfaces.v1;

import com.reddit.vote.common.QueryString;
import com.reddit.vote.common.Uri;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RootController {

	@GetMapping(value = "/")
	public ModelAndView redirect() {
		return new ModelAndView("redirect:" + Uri.V1_REDDIT_TOPICS + QueryString.TOP20);
	}
}
