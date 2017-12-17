package com.kakaobank.booksearch.exception;

import lombok.Getter;

public enum CustomStatus {
    NO_CONTENTS(900, "No contents"),
    SAVE_FAIL(901, "Save Failed"),
    ;

    @Getter
    private final int value;

    @Getter
    private final String reasonPhrase;

    CustomStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }
}
