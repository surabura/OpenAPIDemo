package com.suresh.learning.swagger.swaggerdemo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@ControllerAdvice
public class ExceptionHandling implements ProblemHandling{}
