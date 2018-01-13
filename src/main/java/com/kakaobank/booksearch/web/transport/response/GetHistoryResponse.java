package com.kakaobank.booksearch.web.transport.response;

import com.kakaobank.booksearch.domain.jpa.History;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GetHistoryResponse {
    ArrayList<String> historys;

    public static GetHistoryResponse valueOf(ArrayList<History> historys) {
        GetHistoryResponse getHistoryResponse = new GetHistoryResponse();

        historys.forEach(history -> {
            getHistoryResponse.getHistorys().add(history.getQuery());
        });

        return getHistoryResponse;
    }

}
