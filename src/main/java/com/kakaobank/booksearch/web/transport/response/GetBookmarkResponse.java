package com.kakaobank.booksearch.web.transport.response;

import com.kakaobank.booksearch.domain.jpa.Bookmark;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GetBookmarkResponse {

    ArrayList<Bookmark> bookmarks = new ArrayList<>();

    public static GetBookmarkResponse valueOf(ArrayList<Bookmark> bookmarks) {
        GetBookmarkResponse getHistoryResponse = new GetBookmarkResponse();
        getHistoryResponse.setBookmarks(bookmarks);
        return getHistoryResponse;
    }

}
