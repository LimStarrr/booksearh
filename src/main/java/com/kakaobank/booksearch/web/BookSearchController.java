package com.kakaobank.booksearch.web;

import com.kakaobank.booksearch.domain.kakao.BookSearch;
import com.kakaobank.booksearch.service.BookSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/books")
public class BookSearchController {

    @Autowired
    private BookSearchService bookSearchService;

    @GetMapping("/searches/{title}")
    @ResponseBody
    public BookSearch getBooks(@PathVariable String title) {
        return bookSearchService.getBooks(title);
    }

}
