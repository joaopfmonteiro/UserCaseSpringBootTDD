package com.example.UserCaseSpirng;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserCaseSpirngApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void returnUserWhenDataIsSaved(){
		ResponseEntity<String> response = restTemplate
				.getForEntity("/users/45", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Number id = documentContext.read("$.id");
		String userName = documentContext.read("$.userName");
		String email = documentContext.read("$.email");
		assertThat(id).isEqualTo(45);
		assertThat(userName).isEqualTo("Joao");
		assertThat(email).isEqualTo("joao@gmail.com");
	}

}
