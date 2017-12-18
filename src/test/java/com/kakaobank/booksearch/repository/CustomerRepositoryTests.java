package com.kakaobank.booksearch.repository;

import com.kakaobank.booksearch.domain.jpa.Bookmark;
import com.kakaobank.booksearch.domain.jpa.User;
import com.kakaobank.booksearch.repository.jpa.BookmarkRepository;
import com.kakaobank.booksearch.repository.jpa.UserRepository;
import com.kakaobank.booksearch.web.transport.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testExample() throws Exception {
        User user = new User();
        user.setUserId("user");
        user.setPassword("password");
        this.entityManager.persist(user);
//        User user = this.userRepository.findByUserId("user");
//        assertThat(user.getUsername()).isEqualTo("sboot");
//        assertThat(user.getVin()).isEqualTo("1234");
    }

    @Test
    public void FindByUserIdTest() {
        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(1);
        bookmark.setTitle("Test Bookssssssssss");
        entityManager.persist(bookmark);
        Pagination pagination = new Pagination();

        pagination.setSize(1);
        pagination.setLastId((long) 1);

        List<Bookmark> findByTitleUserIds = bookmarkRepository.findByUserId(bookmark.getUserId(), pagination.toPageRequest());

        assertThat(findByTitleUserIds).extracting(Bookmark::getTitle).containsOnly(bookmark.getTitle());
    }
}
