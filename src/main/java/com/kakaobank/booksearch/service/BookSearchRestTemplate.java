package com.kakaobank.booksearch.service;

import com.kakaobank.booksearch.domain.kakao.BookSearch;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class BookSearchRestTemplate {
    private final RestTemplate restTemplate;

    public BookSearchRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BookSearch getSearchResult(String title) {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://dapi.kakao.com/v2/search/book")
                .queryParam("query", title)
                .build().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK b213e0be9fc3047b54714796f6f0ef5e");
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<BookSearch> bookSearchResponseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, BookSearch.class);

        return bookSearchResponseEntity.getBody();
    }
}
