package com.kakaobank.booksearch.web.transport.response;

import com.kakaobank.booksearch.domain.jpa.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class AccountsSignInResponse {
    private long id;

    private String userId;
    private String password;

    public static AccountsSignInResponse valueOf(User user) {
        AccountsSignInResponse response = new AccountsSignInResponse();
        response.setId(user.getId());
        response.setUserId(user.getUserId());
        response.setPassword(user.getPassword());

        return response;
    }
}
