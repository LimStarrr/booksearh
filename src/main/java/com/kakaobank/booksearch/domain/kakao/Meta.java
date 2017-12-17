package com.kakaobank.booksearch.domain.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {
    boolean isEnd;
    int pageable_count;
    int total_count;
}
