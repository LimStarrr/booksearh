package com.kakaobank.booksearch.service;

import com.kakaobank.booksearch.domain.jpa.History;
import com.kakaobank.booksearch.domain.kakao.BookSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookSearchService {
    @Autowired
    BookSearchRestTemplate bookSearchRestTemplate;

    @Autowired
    HistoryService historyService;

    public BookSearch getBooks(String title) {
        historyService.postHistory(1, title);

        return bookSearchRestTemplate.getSearchResult(title);
    }
}
