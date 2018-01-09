package com.kakaobank.booksearch.service;

import com.kakaobank.booksearch.domain.jpa.User;
import com.kakaobank.booksearch.domain.redis.Session;
import com.kakaobank.booksearch.exception.CustomException;
import com.kakaobank.booksearch.exception.CustomStatus;
import com.kakaobank.booksearch.repository.jpa.UserRepository;
import com.kakaobank.booksearch.repository.redis.SessionRepository;
import com.kakaobank.booksearch.web.transport.request.AccountsSigninPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountsService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    public Session getSession(String sessionCookie) {
        Session session = sessionRepository.findOne(sessionCookie);
        if (session == null) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "Session is expired");
        }

        return session;
    }

    public String getSessionId(User user) {
        Session session = new Session();
        session.setValue(Session.Value.valueOf(user));
        try {
            session = sessionRepository.save(session);
        } catch(IllegalArgumentException e) {
            throw new CustomException(CustomStatus.SAVE_FAIL);
        }

        return session.getId();
    }

    @Transactional
    public User signin(AccountsSigninPostRequest request) {
        User user = new User();
        user.setUserId(request.getUserId());
        user.setPassword(request.getPassword());

        return userRepository.save(user);
    }

}
