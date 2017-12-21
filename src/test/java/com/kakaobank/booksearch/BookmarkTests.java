package com.kakaobank.booksearch;

import com.kakaobank.booksearch.web.transport.request.AccountsSigninPostRequest;
import com.kakaobank.booksearch.web.transport.request.PostBookmarkRequest;
import com.kakaobank.booksearch.web.transport.response.GetBookmarkResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)

public class BookmarkTests {

	@LocalServerPort
	private int port;

	private TestRestTemplate template = new TestRestTemplate();

	@Test
	public void getBookmarksTest() {
		HttpHeaders httpHeaders = new HttpHeaders();

		HttpEntity<String> request = new HttpEntity<>(httpHeaders);
		ResponseEntity<String> response = template.getForEntity("http://localhost:" + port
				+ "/bookmarks", String.class);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	@Test
	public void postBookmarkTest() {
		TestRestTemplate tttest = new TestRestTemplate();
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
		HttpHeaders httpHeaders = getLoginInfo();
		HttpEntity<PostBookmarkRequest> request = new HttpEntity<>(postBookmarkRequest, httpHeaders);

//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Authorization", "KakaoAK b213e0be9fc3047b54714796f6f0ef5e");
		HttpEntity entity = new HttpEntity(httpHeaders);
//
//		ResponseEntity<BookSearch> bookSearchResponseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, BookSearch.class);
//
//		ResponseEntity<String> response = template.exchange("http://localhost:" + port
//				+ "/bookmarks",HttpMethod.POST,request, String.class);

		ResponseEntity<String> response = tttest.exchange("http://localhost:" + port
				+ "/bookmarks",HttpMethod.POST, entity, String.class);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

	}

	@Test
	public void getBookmarkTest() {
//		ResponseEntity<GetBookmarkResponse> response = template.getForEntity("http://localhost:" + port + "/bookmarks", GetBookmarkResponse.class);
//
//		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
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
}
