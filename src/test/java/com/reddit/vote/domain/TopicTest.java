package com.reddit.vote.domain;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TopicTest {
	private static Validator validator;

	@BeforeClass
	public static void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void checkNull() throws Exception {
		// Given
		Topic nullTopic = new Topic();

		// When
		Set<ConstraintViolation<Topic>> constraintViolations = validator.validate(nullTopic);

		// Then
		assertThat(constraintViolations.size()).isEqualTo(1);
	}

	@Test
	public void checkEmpty() throws Exception {
		// Given
		Topic emptyTopic = new Topic("");

		// When
		Set<ConstraintViolation<Topic>> constraintViolations = validator.validate(emptyTopic);

		// Then
		assertThat(constraintViolations.size()).isEqualTo(1);
	}

	@Test
	public void checkValid() throws Exception {
		// Given
		Topic validTopic = new Topic(String.valueOf(new char[255]));

		// When
		Set<ConstraintViolation<Topic>> validate = validator.validate(validTopic);

		// Then
		assertThat(validate.size()).isEqualTo(0);
	}

	@Test
	public void checkMaximumLength() throws Exception {
		// Given
		Topic invalidTopic = new Topic(String.valueOf(new char[256]));

		// When
		Set<ConstraintViolation<Topic>> constraintViolations = validator.validate(invalidTopic);

		// Then
		assertThat(constraintViolations.size()).isEqualTo(1);
	}
}
