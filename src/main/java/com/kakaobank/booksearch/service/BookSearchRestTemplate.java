package com.kakaobank.booksearch.service;

import com.kakaobank.booksearch.config.property.Kakao;
import com.kakaobank.booksearch.domain.kakao.BookSearch;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Kakao kakao;

    public BookSearchRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

//    public BookSearch getSearchResult(String title) {
//        URI uri = UriComponentsBuilder.fromHttpUrl(kakao.getUrl())
//                .queryParam("query", title)
//                .build().toUri();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", kakao.getAuthorization());
//        HttpEntity entity = new HttpEntity(headers);
//
//        ResponseEntity<BookSearch> bookSearchResponseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, BookSearch.class);
//
//        return bookSearchResponseEntity.getBody();
//    }
}
