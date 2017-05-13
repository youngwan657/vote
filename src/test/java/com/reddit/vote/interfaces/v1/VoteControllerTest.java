package com.reddit.vote.interfaces.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reddit.vote.domain.VoteType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VoteControllerTest {

	private MockMvc mvc;
	private ObjectMapper mapper;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		mvc = webAppContextSetup(webApplicationContext).build();
		mapper = new ObjectMapper();
	}

	@Test
	public void vote() throws Exception {
		final String VOTE_URL = "/v1/reddit/topics/1";
		mvc.perform(post(VOTE_URL)
			.param("upDown", VoteType.UP.name()))
			.andExpect(status().is3xxRedirection());
	}
}
