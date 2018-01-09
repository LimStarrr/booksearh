package com.kakaobank.booksearch.repository.redis;

import com.kakaobank.booksearch.domain.redis.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {

}
