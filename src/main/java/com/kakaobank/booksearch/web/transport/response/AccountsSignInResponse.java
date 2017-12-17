package com.kakaobank.booksearch.web.transport.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class AccountsSignInResponse {
    private int id;

//    private LocalDateTime createdAt;
//
//    public static AccountsSignInResponse valueOf(User user) {
//        AccountsSignInResponse response = new AccountsSignInResponse();
//        response.setId(user.getId());
//        response.setEmail(user.getEmail());
//        response.setNickName(user.getNickname());
//        response.setSocialType(user.getSocialType());
//        response.setCreatedAt(user.getCreatedAt());
//        return response;
//    }
}
