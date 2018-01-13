package com.kakaobank.booksearch.web.transport.request;


import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum SortType {
    TITLE(0,"title"),
    DATETIME(1, "datetime"),
    CATEGORY(2, "category");

    @Getter
    int type;

    @Getter
    String name;
}
