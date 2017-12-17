package com.kakaobank.booksearch.service;

import com.kakaobank.booksearch.domain.jpa.History;
import com.kakaobank.booksearch.repository.jpa.HistoryRepository;
import com.kakaobank.booksearch.web.transport.response.GetHistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    public GetHistoryResponse getHistory(long userId) {
        ArrayList<History> histories = historyRepository.findByUserId(userId);
        return GetHistoryResponse.valueOf(histories);
    }
    

    @Transactional
    public void postHistory(long userId, String query) {
        History history = new History();
        history.setUserId(userId);
        history.setQuery(query);

        historyRepository.save(history);
    }
}
