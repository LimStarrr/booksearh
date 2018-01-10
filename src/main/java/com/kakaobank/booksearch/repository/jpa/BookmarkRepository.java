package com.kakaobank.booksearch.repository.jpa;

import com.kakaobank.booksearch.domain.jpa.Bookmark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    ArrayList<Bookmark> findAllByUserIdAndIdLessThan(long userId, long id, Pageable pageable);

}
