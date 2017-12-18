package com.kakaobank.booksearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobank.booksearch.web.transport.request.AccountsSigninPostRequest;
import com.kakaobank.booksearch.web.transport.response.AccountsSignInResponse;
import net.minidev.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Connection;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BooksearchApplicationTests{

	@LocalServerPort
	private int port;

	private TestRestTemplate template = new TestRestTemplate();

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}
//
//	private HttpHeaders setHeaders(String app, String clientId) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.set(APP_HEADER, app);
//		headers.set(CLIENT_HEADER, clientId);
//		return headers;
//	}


	@Test
	public void homePageLoads() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:"
				+ port + "/", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void userEndpointProtected() {
//		TestRestTemplate template = new TestRestTemplate("user", "password");
//		TestRestTemplate template = new TestRestTemplate("user", "password");
//
//		ResponseEntity<String> response = template.getForEntity("http://localhost:"
//				+ port + "/history", template, String.class);
//
////		HttpHeaders headers = setHeaders("appTest", "clientTest");
//
////		ResponseEntity<String> response  = template.exchange("http://localhost:"
////				+ port + "/signin", HttpMethod.POST, template, String.class);
//
//		ResponseEntity<String> response  = template.exchange("http://localhost:"
//				+ port + "/signin", HttpMethod.POST, template, String.class);
//
//		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
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
