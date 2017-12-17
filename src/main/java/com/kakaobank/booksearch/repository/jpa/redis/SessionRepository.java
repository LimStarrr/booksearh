package com.kakaobank.booksearch.repository.jpa.redis;

import com.kakaobank.booksearch.domain.redis.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, String> {

}
