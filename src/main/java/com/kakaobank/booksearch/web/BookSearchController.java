package com.kakaobank.booksearch.web;

import com.kakaobank.booksearch.common.Attribute;
import com.kakaobank.booksearch.domain.kakao.BookSearch;
import com.kakaobank.booksearch.domain.redis.Session;
import com.kakaobank.booksearch.service.BookSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class BookSearchController {

    @Autowired
    private BookSearchService bookSearchService;

    @GetMapping("/books/{title}/{page}")
    @ResponseBody
    public BookSearch getBooks(@RequestAttribute(Attribute.SESSION) Session session, @PathVariable String title, @PathVariable String page ) {
        return bookSearchService.getBooks(session.getValue().getUserId(), title, page);
    }



}
