package com.kakaobank.booksearch.repository;

import com.kakaobank.booksearch.domain.jpa.History;
import com.kakaobank.booksearch.domain.jpa.User;
import com.kakaobank.booksearch.repository.jpa.HistoryRepository;
import com.kakaobank.booksearch.repository.jpa.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Test
    public void userRepositoryTest() throws Exception {
        User user = new User();
        user.setUserId("user");
        user.setPassword("password");
        this.entityManager.persist(user);
        User userRepositoryByUserId = this.userRepository.findByUserId("user");
        assertThat(userRepositoryByUserId.getUserId()).isEqualTo("user");
        assertThat(userRepositoryByUserId.getPassword()).isEqualTo("password");
    }

    @Test
    public void historyRepositoryTest() throws Exception {
        ArrayList<History> histories = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            History history = new History();
            history.setUserId(1);
            history.setQuery("test" + j);
            this.entityManager.persist(history);
            histories.add(history);
        }

        ArrayList<History> historyRepositoryByUserId = this.historyRepository.findAllByUserId(1);
        for (int i = 0; i < historyRepositoryByUserId.size() ; i++) {
            assertThat(historyRepositoryByUserId.get(i).getQuery()).isEqualTo("test" + i);
        }
    }
}
