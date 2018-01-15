package com.kakaobank.booksearch.repository.jpa;

import com.kakaobank.booksearch.domain.jpa.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    ArrayList<History> findAllByUserId(long userId);
}
