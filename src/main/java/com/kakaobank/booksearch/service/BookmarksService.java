package com.kakaobank.booksearch.service;

import com.kakaobank.booksearch.domain.jpa.Bookmark;
import com.kakaobank.booksearch.exception.CustomException;
import com.kakaobank.booksearch.repository.jpa.BookmarkRepository;
import com.kakaobank.booksearch.web.transport.Pagination;
import com.kakaobank.booksearch.web.transport.request.PostBookmarkRequest;
import com.kakaobank.booksearch.web.transport.response.GetBookmarkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class BookmarksService {

    @Autowired
    BookmarkRepository bookmarkRepository;

    public GetBookmarkResponse getBookmarks(long userId, Pagination pagination) {
        Page<Bookmark> bookmarks = bookmarkRepository.findAllByUserId(userId, pagination.toPageOffsetRequest());

        if(bookmarks.getTotalElements() <= 0)
            throw new CustomException(HttpStatus.NO_CONTENT);

        return GetBookmarkResponse.valueOf(bookmarks);
    }

    @Transactional
    public void postBookmarks(long userId, PostBookmarkRequest request) {
        Bookmark bookmark = new Bookmark();

        bookmark.setUserId(userId);
        bookmark.setAuthors(request.getAuthors());
        bookmark.setBarcode(request.getBarcode());
        bookmark.setCategory(request.getCategory());
        bookmark.setContents(request.getContents());
        bookmark.setEbook_barcode(request.getEbook_barcode());
        bookmark.setIsbn(request.getIsbn());
        bookmark.setPrice(request.getPrice());
        bookmark.setSale_price(request.getSale_price());
        bookmark.setSale_yn(request.getSale_yn());
        bookmark.setThumbnail(request.getThumbnail());
        bookmark.setTitle(request.getTitle());
        bookmark.setUrl(request.getUrl());

        bookmark.setDatetime(
                ZonedDateTime.parse(request.getDatetime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
//        bookmark.setDatetime(LocalDateTime.parse(request.getDatetime()));
//                ZonedDateTime.parse(request.getDatetime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        bookmarkRepository.save(bookmark);
    }

    @Transactional
    public Bookmark deleteBookmarks(long userId, String barcode) {
        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(userId);
        bookmark.setBarcode(barcode);

        bookmarkRepository.delete(bookmark);

        return bookmark;
    }
}
