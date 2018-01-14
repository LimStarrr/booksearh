package com.kakaobank.booksearch.service;

import com.kakaobank.booksearch.config.property.Kakao;
import com.kakaobank.booksearch.domain.kakao.BookSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookSearchService {

//    private final RestTemplate restTemplate;

    @Autowired
    private Kakao kakao;

    @Autowired
    HistoryService historyService;

    public BookSearch getBooks(long userId, String title, String page) {
        historyService.postHistory(userId, title);
        return getSearchResult(title, page);
//        return bookSearchRestTemplate.getSearchResult(title);
    }

    public BookSearch getSearchResult(String title, String page) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> queryMaps = new LinkedMultiValueMap<String, String>();
        queryMaps.add("query", title);
        queryMaps.add("page", page);

        URI uri = UriComponentsBuilder.fromHttpUrl(kakao.getUrl())
//                .queryParam("query", title)
                .queryParams(queryMaps)
                .build().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", kakao.getAuthorization());
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<BookSearch> bookSearchResponseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, BookSearch.class);

        return bookSearchResponseEntity.getBody();
    }
}
