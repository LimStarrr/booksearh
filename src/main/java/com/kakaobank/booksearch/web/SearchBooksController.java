package com.kakaobank.booksearch.web;

import com.kakaobank.booksearch.domain.kakao.BookSearch;
import com.kakaobank.booksearch.service.SearchBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchBooksController {

    @Autowired
    private SearchBooksService searchBooksService;

    @GetMapping("/books/{title}/{page}")
    @ResponseBody
    public BookSearch getBooks(@PathVariable String title, @PathVariable String page ) {
        return searchBooksService.getBooks(title, page);
    }
}
