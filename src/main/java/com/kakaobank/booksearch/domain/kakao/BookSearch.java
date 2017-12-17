package com.kakaobank.booksearch.domain.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookSearch {
    @JsonProperty("documents")
    List<Books> books;

    @JsonProperty("meta")
    Meta meta;
}
