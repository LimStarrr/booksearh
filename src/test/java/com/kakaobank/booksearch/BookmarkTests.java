package com.kakaobank.booksearch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kakaobank.booksearch.domain.jpa.Bookmark;
import com.kakaobank.booksearch.domain.jpa.User;
import com.kakaobank.booksearch.web.transport.Pagination;
import com.kakaobank.booksearch.web.transport.request.AccountsSigninPostRequest;
import com.kakaobank.booksearch.web.transport.request.PostBookmarkRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)

public class BookmarkTests {

	@LocalServerPort
	private int port;

	private TestRestTemplate template = new TestRestTemplate();


	private HttpHeaders httpHeaders = new HttpHeaders();

	@Before
	public void setup() {
		User user = new User();
		user.setUserId("user");
		user.setPassword("password");

		ResponseEntity<User> response = template.postForEntity("http://localhost:" + port
				+ "/signin", user, User.class);
		httpHeaders = response.getHeaders();
		Assert.assertNotNull(httpHeaders);
	}

	@Test
	public void getBookmarksTest() {
		HttpHeaders headers = new HttpHeaders();
		String cookie = httpHeaders.getFirst("Set-Cookie");
		headers.add("Cookie", cookie);


		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = template.exchange("http://localhost:" + port
				+ "/bookmarks", HttpMethod.GET, entity, String.class);
	}

	@Test
	public void postBookmarkTest() {
		PostBookmarkRequest postBookmarkRequest = new PostBookmarkRequest();

		postBookmarkRequest.setTitle("tatq");
		ArrayList<String> authors = new ArrayList<>();
		authors.add("조조 모예스");
		postBookmarkRequest.setAuthors(authors);
		postBookmarkRequest.setBarcode("KOR9788952227829");
		postBookmarkRequest.setCategory("소설");
		postBookmarkRequest.setContents("맞닿을 것 하나 없이 다른 둘이 만나 하나의 꿈을 꾸다!  조조 모예스의 소설 『미 비포 유(Me Before You)』. 꿈같은 삶을 산 남자와 꿈을 선물 받은 여자의 이야기를 담은 작품이다. 오만..");
		postBookmarkRequest.setDatetime("2014-01-01T00:00:00.000+09:00");
		postBookmarkRequest.setEbook_barcode("DGT00022374570AL");
		postBookmarkRequest.setIsbn("Y");
		postBookmarkRequest.setPrice(15000);
		postBookmarkRequest.setPublisher("살림");
		postBookmarkRequest.setSale_price(1111);
		postBookmarkRequest.setSale_yn("Y");
		postBookmarkRequest.setStatus("progressing");
		postBookmarkRequest.setThumbnail("http://t1.daumcdn.net/thumb/R72x100/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FENG9780738604220%3Fmoddttm=20170901060559");
		postBookmarkRequest.setTitle("title");
		postBookmarkRequest.setTranslators(new ArrayList<>());
		postBookmarkRequest.setUrl("http://book.daum.net/detail/book.do?bookid=ENG9780738604220");

		HttpHeaders headers = new HttpHeaders();
		String cookie = httpHeaders.getFirst("Set-Cookie");
		headers.add("Cookie", cookie);

		HttpEntity entity = new HttpEntity(postBookmarkRequest, headers);

		ResponseEntity<String> response = template.exchange("http://localhost:" + port
				+ "/bookmarks", HttpMethod.POST, entity, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void getBookmarkTest() {
		PostBookmarkRequest postBookmarkRequest = prePostBookmark();

		HttpHeaders headers = new HttpHeaders();
		String cookie = httpHeaders.getFirst("Set-Cookie");
		headers.add("Cookie", cookie);
		Pagination pagination = new Pagination();
		pagination.setLastId(0);
		pagination.setSize(10);
		pagination.setSortType(0);

		MultiValueMap<String, String> queryMaps = new LinkedMultiValueMap<String, String>();
		queryMaps.add("lastId", "0");
		queryMaps.add("size", "10");
		queryMaps.add("sortType", "0");

		URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/bookmarks")
				.queryParams(queryMaps)
				.build().toUri();

		HttpEntity entity = new HttpEntity(headers);

		ResponseEntity<String> response = template.exchange(uri, HttpMethod.GET, entity, String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		List<Bookmark> bookmarks = null;
		try {
			JsonNode rootNode = objectMapper.readTree(response.getBody());
			JsonNode bookmark = rootNode.path("bookmarks");
			JsonNode content = bookmark.path("content");
			if(!content.isNull() && content.isArray()) {
				bookmarks = objectMapper.readValue(content.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, Bookmark.class));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals(HttpStatus.OK, response.getStatusCode());

		assertEquals(1, bookmarks.size());

		assertEquals(postBookmarkRequest.getBarcode(), bookmarks.get(0).getBarcode());
		assertEquals(postBookmarkRequest.getCategory(), bookmarks.get(0).getCategory());
		assertEquals(postBookmarkRequest.getContents(), bookmarks.get(0).getContents());
		assertEquals(postBookmarkRequest.getEbook_barcode(), bookmarks.get(0).getEbook_barcode());
		assertEquals(postBookmarkRequest.getPrice(), bookmarks.get(0).getPrice());
		assertEquals(postBookmarkRequest.getSale_price(), bookmarks.get(0).getSale_price());
	}

	@Test
	public void deleteBookmarkTest() {
//		template.delete("http://localhost:" + port + "/bookmarks");
//		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	public HttpHeaders getLoginInfo() {
		TestRestTemplate template = new TestRestTemplate();

		AccountsSigninPostRequest accountsSigninPostRequest = new AccountsSigninPostRequest();
		accountsSigninPostRequest.setUserId("user");
		accountsSigninPostRequest.setPassword("password");

		HttpEntity<AccountsSigninPostRequest> request = new HttpEntity<>(accountsSigninPostRequest);

		ResponseEntity<String> response = template.exchange("http://localhost:" + port
				+ "/signin",HttpMethod.POST, request, String.class);

		return response.getHeaders();
	}

	private PostBookmarkRequest prePostBookmark() {
		PostBookmarkRequest postBookmarkRequest = new PostBookmarkRequest();
		postBookmarkRequest.setTitle("tatq");
		ArrayList<String> authors = new ArrayList<>();
		authors.add("조조 모예스");
		postBookmarkRequest.setAuthors(authors);
		postBookmarkRequest.setBarcode("KOR9788952227829");
		postBookmarkRequest.setCategory("소설");
		postBookmarkRequest.setContents("맞닿을 것 하나 없이 다른 둘이 만나 하나의 꿈을 꾸다!  조조 모예스의 소설 『미 비포 유(Me Before You)』. 꿈같은 삶을 산 남자와 꿈을 선물 받은 여자의 이야기를 담은 작품이다. 오만..");
		postBookmarkRequest.setDatetime("2014-01-01T00:00:00.000+09:00");
		postBookmarkRequest.setEbook_barcode("DGT00022374570AL");
		postBookmarkRequest.setIsbn("Y");
		postBookmarkRequest.setPrice(15000);
		postBookmarkRequest.setPublisher("살림");
		postBookmarkRequest.setSale_price(1111);
		postBookmarkRequest.setSale_yn("Y");
		postBookmarkRequest.setStatus("progressing");
		postBookmarkRequest.setThumbnail("http://t1.daumcdn.net/thumb/R72x100/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FENG9780738604220%3Fmoddttm=20170901060559");
		postBookmarkRequest.setTitle("title");
		postBookmarkRequest.setTranslators(new ArrayList<>());
		postBookmarkRequest.setUrl("http://book.daum.net/detail/book.do?bookid=ENG9780738604220");

		HttpHeaders headers = new HttpHeaders();
		String cookie = httpHeaders.getFirst("Set-Cookie");
		headers.add("Cookie", cookie);

		HttpEntity entity = new HttpEntity(postBookmarkRequest, headers);

		ResponseEntity<String> response = template.exchange("http://localhost:" + port
				+ "/bookmarks", HttpMethod.POST, entity, String.class);

		return postBookmarkRequest;
	}

}
