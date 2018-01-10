package com.kakaobank.booksearch.domain.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.net.URI;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Books {
    long id;
    List<String> authors;
    String barcode;
    String category;
    String contents;

    String datetime;

    String ebook_barcode;
    String isbn;
    int price;
    String publisher;
    int sale_price;
    String sale_yn;
    String status;
    String thumbnail;
    String title;
    List<String> translators;
    URI url;
}
