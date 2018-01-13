package com.kakaobank.booksearch.web.transport.request;


import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum SortType {
    TITLE(0,"title"),
    DATETIME(1, "datetime"),
    PRICE(2, "price");

    @Getter
    int type;

    @Getter
    String name;
}
