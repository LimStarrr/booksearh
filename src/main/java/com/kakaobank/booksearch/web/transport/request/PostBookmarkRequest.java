package com.kakaobank.booksearch.web.transport.request;

import com.kakaobank.booksearch.domain.jpa.Bookmark;
import com.kakaobank.booksearch.domain.kakao.Books;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;

/*
{
            "authors": [
                "Derek Townsend"
            ],
            "barcode": "BOK00031805145KA",
            "category": "",
            "contents": "",
            "datetime": "2016-11-22T00:00:00.000+09:00",
            "ebook_barcode": "",
            "isbn": " 9781489137821",
            "price": 43800,
            "publisher": "Emereo Publishing",
            "sale_price": 43800,
            "sale_yn": "N",
            "status": "가격 확인 중",
            "thumbnail": "",
            "title": "The Acceptance Test Handbook - Everything You Need to Know about...",
            "translators": [],
            "url": "http://book.daum.net/detail/book.do?bookid=BOK00031805145KA"
        },
 */
@Data
@ToString
@EqualsAndHashCode
public class PostBookmarkRequest {
    ArrayList<String> authors;
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
    ArrayList<String> translators;
    String url;
}
