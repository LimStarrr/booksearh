package com.kakaobank.booksearch.web.transport.response;

import com.kakaobank.booksearch.domain.jpa.Bookmark;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class GetBookmarkResponse {

    Page<Bookmark> bookmarks;

    public static GetBookmarkResponse valueOf(Page<Bookmark> bookmarks) {
        GetBookmarkResponse getHistoryResponse = new GetBookmarkResponse();
        getHistoryResponse.setBookmarks(bookmarks);

        return getHistoryResponse;
    }

}
