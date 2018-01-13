package com.kakaobank.booksearch.web.transport.response;

import com.kakaobank.booksearch.domain.jpa.History;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GetHistoryResponse {
    ArrayList<String> histories = new ArrayList<>();

    public static GetHistoryResponse valueOf(ArrayList<History> histories) {
        GetHistoryResponse getHistoryResponse = new GetHistoryResponse();
        histories.forEach(history -> {
            getHistoryResponse.getHistories().add(history.getQuery());
        });

        return getHistoryResponse;
    }

}
