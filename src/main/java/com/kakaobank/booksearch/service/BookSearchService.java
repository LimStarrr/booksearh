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

    public BookSearch getBooks(long userId, String title) {
        historyService.postHistory(userId, title);

        return bookSearchRestTemplate.getSearchResult(title);
    }
}
