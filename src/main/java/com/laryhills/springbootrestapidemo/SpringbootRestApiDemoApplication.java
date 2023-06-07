package com.laryhills.springbootrestapidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootRestApiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestApiDemoApplication.class, args);
	}

	@GetMapping("/")
	public String greet(){
		return "Hello World";
	}

	@GetMapping("/api")
	public CustomResponse apiGreeting(){
		return new CustomResponse("success", "Welcome to SpringBoot Demo API");
	}

	public static class CustomResponse {
		private String status;
		private String message;

		public CustomResponse(String status, String message) {
			this.status = status;
			this.message = message;
		}

		public String getStatus() {
			return status;
		}

		public String getMessage() {
			return message;
		}
	}

}
