package com.kakaobank.booksearch.web.transport.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;

@Data
@ToString
@EqualsAndHashCode
public class AccountsSigninPostRequest {
    @NotEmpty
    String userId;
    @NotEmpty
    String password;
}
