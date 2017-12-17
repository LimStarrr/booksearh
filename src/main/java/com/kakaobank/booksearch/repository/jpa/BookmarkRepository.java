package com.kakaobank.booksearch.repository.jpa;

import com.kakaobank.booksearch.domain.jpa.Bookmark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface BookmarkRepository extends CrudRepository<Bookmark, Long> {
    ArrayList<Bookmark> findByUserId(long userId, Pageable pageable);
}
