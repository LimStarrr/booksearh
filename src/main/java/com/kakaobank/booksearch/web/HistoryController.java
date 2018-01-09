package com.kakaobank.booksearch.web;

import com.kakaobank.booksearch.common.Attribute;
import com.kakaobank.booksearch.domain.redis.Session;
import com.kakaobank.booksearch.service.HistoryService;
import com.kakaobank.booksearch.web.transport.response.GetHistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping
    @ResponseBody
    public GetHistoryResponse getHistory(@RequestAttribute(Attribute.SESSION) Session session) {
        return historyService.getHistory(session.getValue().getUserId());
    }
}
