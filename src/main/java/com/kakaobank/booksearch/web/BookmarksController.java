package com.kakaobank.booksearch.web;

import com.kakaobank.booksearch.common.Attribute;
import com.kakaobank.booksearch.domain.redis.Session;
import com.kakaobank.booksearch.service.BookmarksService;
import com.kakaobank.booksearch.web.transport.Pagination;
import com.kakaobank.booksearch.web.transport.request.PostBookmarkRequest;
import com.kakaobank.booksearch.web.transport.response.GetBookmarkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmarks")
public class BookmarksController {

    @Autowired
    BookmarksService bookmarksService;

    @PostMapping
    public void postBookmarks(@RequestBody PostBookmarkRequest request) {
        bookmarksService.postBookmarks(request);
    }

    @GetMapping
    @ResponseBody
    public GetBookmarkResponse getBookmarks(@RequestAttribute(Attribute.SESSION) Session session,
                                            @ModelAttribute Pagination pagination) {
        return bookmarksService.getBookmarks(session.getValue().getUserId(), pagination);
    }

    @DeleteMapping("/{bookmarkId}")
    public void deleteBookmarks(@RequestAttribute(Attribute.SESSION) Session session, @PathVariable int bookmarkId) {
        bookmarksService.deleteBookmarks(session.getValue().getUserId(), bookmarkId );
    }
}
