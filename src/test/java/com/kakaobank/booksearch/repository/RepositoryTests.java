package com.kakaobank.booksearch.repository;

import com.kakaobank.booksearch.domain.jpa.Bookmark;
import com.kakaobank.booksearch.domain.jpa.History;
import com.kakaobank.booksearch.domain.jpa.User;
import com.kakaobank.booksearch.repository.jpa.BookmarkRepository;
import com.kakaobank.booksearch.repository.jpa.HistoryRepository;
import com.kakaobank.booksearch.repository.jpa.UserRepository;
import com.kakaobank.booksearch.web.transport.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private BookmarkRepository bookmarkRepository;
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
        long userId = 1;
        ArrayList<History> histories = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            History history = new History();
            history.setUserId(userId);
            history.setQuery("test" + j);
            this.entityManager.persist(history);
            histories.add(history);
        }

        ArrayList<History> historyRepositoryByUserId = this.historyRepository.findAllByUserId(userId);
        for (int i = 0; i < historyRepositoryByUserId.size() ; i++) {
            assertThat(historyRepositoryByUserId.get(i).getQuery()).isEqualTo("test" + i);
        }
    }

    @Test
    public void bookmarkRepositoryTest() throws Exception {
        for (int i = 0; i < 20; i++) {
            Bookmark bookmark = createBookmark(i);
            bookmarkRepository.save(bookmark);
        }

        Pagination pagination = new Pagination();
        pagination.setSize(10);
        pagination.setLastId(1);
        pagination.setSortType(0);

        Page<Bookmark> bookmarks = bookmarkRepository.findAllByUserId(1, pagination.toPageOffsetRequest());

        //Total Elements is 20.
        assertThat(bookmarks.getTotalElements()).isEqualTo(20);

        //each 1 page elements is 10.
        assertThat(bookmarks.getSize()).isEqualTo(10);

        pagination.setLastId(2);

        bookmarks = bookmarkRepository.findAllByUserId(1, pagination.toPageOffsetRequest());

        assertThat(bookmarks.getSize()).isEqualTo(10);
    }


    private Bookmark createBookmark(int num) {
        Bookmark bookmark = new Bookmark();

        bookmark.setUserId(1);
        bookmark.setBarcode("KOR9788952227829" + num);
        bookmark.setTitle("tatq");
        ArrayList<String> authors = new ArrayList<>();
        authors.add("조조 모예스");
        bookmark.setAuthors(authors);
        bookmark.setCategory("소설");
        bookmark.setContents("맞닿을 것 하나 없이 다른 둘이 만나 하나의 꿈을 꾸다!  조조 모예스의 소설 『미 비포 유(Me Before You)』. 꿈같은 삶을 산 남자와 꿈을 선물 받은 여자의 이야기를 담은 작품이다. 오만..");

        bookmark.setDatetime(
                ZonedDateTime.parse("2018-01-15T00:00:00.000+09:00",
                        DateTimeFormatter.ISO_OFFSET_DATE_TIME));

//        bookmark.setDatetime(LocalDateTime.parse("2018-01-15T00:00:00.000+09:00"));

        bookmark.setEbook_barcode("DGT00022374570AL");
        bookmark.setIsbn("Y");
        bookmark.setPrice(15000);
        bookmark.setPublisher("살림");
        bookmark.setSale_price(1111);
        bookmark.setSale_yn("Y");
        bookmark.setStatus("progressing");
        bookmark.setThumbnail("http://t1.daumcdn.net/thumb/R72x100/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FENG9780738604220%3Fmoddttm=20170901060559");
        bookmark.setTitle(num + "title");
        bookmark.setTranslators(new ArrayList<>());
        bookmark.setUrl("http://book.daum.net/detail/book.do?bookid=ENG9780738604220");

        return bookmark;
    }
}
