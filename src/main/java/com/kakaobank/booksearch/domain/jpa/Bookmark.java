package com.kakaobank.booksearch.domain.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class Bookmark {

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
    @Id
    String barcode;

    private long userId;

    ArrayList<String> authors;
    String category;
    String contents;
    ZonedDateTime datetime;
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
