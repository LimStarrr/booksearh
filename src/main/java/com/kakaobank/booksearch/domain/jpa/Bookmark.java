package com.kakaobank.booksearch.domain.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userId;

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
