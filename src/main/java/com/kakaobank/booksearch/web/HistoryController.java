package com.kakaobank.booksearch.web;

import com.kakaobank.booksearch.common.Attribute;
import com.kakaobank.booksearch.domain.redis.Session;
import com.kakaobank.booksearch.service.HistoryService;
import com.kakaobank.booksearch.web.transport.response.GetHistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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
