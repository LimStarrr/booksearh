package com.kakaobank.booksearch.service;

import com.kakaobank.booksearch.domain.jpa.Bookmark;
import com.kakaobank.booksearch.exception.CustomException;
import com.kakaobank.booksearch.exception.CustomStatus;
import com.kakaobank.booksearch.repository.jpa.BookmarkRepository;
import com.kakaobank.booksearch.web.transport.Pagination;
import com.kakaobank.booksearch.web.transport.request.PostBookmarkRequest;
import com.kakaobank.booksearch.web.transport.request.SortType;
import com.kakaobank.booksearch.web.transport.response.GetBookmarkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class BookmarksService {

    @Autowired
    BookmarkRepository bookmarkRepository;

    public GetBookmarkResponse getBookmarks(long userId, Pagination pagination) {
//        ArrayList<Bookmark> bookmarks = bookmarkRepository.findAllByUserIdAndIdLessThan(userId, pagination.getLastId(), pagination.toPageRequest());
        Page<Bookmark> bookmarks = bookmarkRepository.findAllByUserId(userId, pagination.toPageOffsetRequest());

        if(bookmarks.getTotalElements() <= 0)
            throw new CustomException(CustomStatus.NO_CONTENTS);

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

        bookmarkRepository.save(bookmark);
    }

    @Transactional
    public void deleteBookmarks(long userId, int bookmarkId) {
        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(userId);
        bookmark.setId(bookmarkId);

        bookmarkRepository.delete(bookmark);
    }
}
