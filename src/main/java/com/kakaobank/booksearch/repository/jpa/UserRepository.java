package com.kakaobank.booksearch.repository.jpa;

import com.kakaobank.booksearch.domain.jpa.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserId(String userId);
}
