package com.kakaobank.booksearch.web;

import com.kakaobank.booksearch.common.Attribute;
import com.kakaobank.booksearch.domain.redis.Session;
import com.kakaobank.booksearch.service.BookmarksService;
import com.kakaobank.booksearch.web.transport.Pagination;
import com.kakaobank.booksearch.web.transport.request.PostBookmarkRequest;
import com.kakaobank.booksearch.web.transport.request.SortType;
import com.kakaobank.booksearch.web.transport.response.GetBookmarkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bookmarks")
public class BookmarksController {

    @Autowired
    BookmarksService bookmarksService;


//    @PostMapping
//    public void postBookmarks(@RequestAttribute(Attribute.SESSION) Session session) {
//        bookmarksService.postBookmarks(session.getValue().getUserId(), null);
//    }

    @PostMapping
    @ResponseBody
    public String postBookmarks(@RequestAttribute(Attribute.SESSION) Session session,
                                @RequestBody @Valid PostBookmarkRequest request) {
        bookmarksService.postBookmarks(session.getValue().getUserId(), request);
//        bookmarksService.postBookmarks(session.getValue().getUserId(), null);
        return null;
    }

    @GetMapping("")
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
