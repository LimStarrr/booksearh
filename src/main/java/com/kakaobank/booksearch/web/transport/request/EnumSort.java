package com.kakaobank.booksearch.web.transport.request;

import lombok.Getter;

public enum EnumSort {
    TITLE(0, "title"),
    DATETIME(1, "datetime"),
    ;

    @Getter
    private final int value;

    @Getter
    private final String reasonPhrase;

    EnumSort(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

}
