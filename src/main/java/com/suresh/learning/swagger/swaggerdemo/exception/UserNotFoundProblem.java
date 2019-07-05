package com.suresh.learning.swagger.swaggerdemo.exception;

import java.net.URI;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class UserNotFoundProblem extends AbstractThrowableProblem {

	private static final URI TYPE = URI.create("http://standardbank.co.za");
	
	public UserNotFoundProblem(int idNumber) {
		super(TYPE, "User Not Found", Status.NOT_FOUND,
				String.format("User with Id '%s' not found", idNumber));
	}

}
