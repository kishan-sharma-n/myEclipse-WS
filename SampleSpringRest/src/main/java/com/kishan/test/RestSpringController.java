package com.kishan.test;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestSpringController {
	
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/test/rest", method = RequestMethod.GET)
	public @ResponseBody Greeting sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
		logger.debug("Entering and exiting sayHello");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
        
    }

}
