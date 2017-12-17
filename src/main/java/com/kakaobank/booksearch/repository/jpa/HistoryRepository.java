package com.kakaobank.booksearch.repository.jpa;

import com.kakaobank.booksearch.domain.jpa.History;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface HistoryRepository extends CrudRepository<History, Long> {
    ArrayList<History> findByUserId(long userId);
}
