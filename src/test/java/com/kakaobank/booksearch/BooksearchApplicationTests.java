package com.kakaobank.booksearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobank.booksearch.web.transport.request.AccountsSigninPostRequest;
import com.kakaobank.booksearch.web.transport.response.AccountsSignInResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BooksearchApplicationTests{

	@LocalServerPort
	private int port;

	private TestRestTemplate template = new TestRestTemplate();


	@Test
	public void homePageLoads() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:"
				+ port + "/", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void loginTest() {
		TestRestTemplate template = new TestRestTemplate();

		AccountsSigninPostRequest accountsSigninPostRequest = new AccountsSigninPostRequest();
		accountsSigninPostRequest.setUserId("user");
		accountsSigninPostRequest.setPassword("password");

		HttpEntity<AccountsSigninPostRequest> request = new HttpEntity<>(accountsSigninPostRequest);

		ResponseEntity<String> response = template.exchange("http://localhost:" + port
				+ "/signin",HttpMethod.POST, request, String.class);

		//Response 200 Ok
		assertEquals(HttpStatus.OK, response.getStatusCode());

		ObjectMapper objectMapper = new ObjectMapper();
		AccountsSignInResponse accountsSignInResponse = null;
		try {
			accountsSignInResponse = objectMapper.readValue(response.getBody(), AccountsSignInResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Input userId : user, Ouput userId : user
		assertEquals(accountsSigninPostRequest.getUserId(), accountsSignInResponse.getUserId());
		//Input password : password, Ouput password : password
		assertEquals(accountsSigninPostRequest.getPassword(), accountsSignInResponse.getPassword());
	}


}
