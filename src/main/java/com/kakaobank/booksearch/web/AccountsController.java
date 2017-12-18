package com.kakaobank.booksearch.web;

import com.kakaobank.booksearch.common.HttpHeader;
import com.kakaobank.booksearch.domain.jpa.User;
import com.kakaobank.booksearch.service.AccountsService;
import com.kakaobank.booksearch.web.transport.request.AccountsSigninPostRequest;
import com.kakaobank.booksearch.web.transport.response.AccountsSignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class AccountsController {

    @Autowired
    private AccountsService accountsService;

    @PostMapping("/signin")
    @ResponseBody
    public AccountsSignInResponse signin(@RequestBody @Valid AccountsSigninPostRequest request,
                               HttpServletResponse response) {
        CookieGenerator cookieGenerator = new CookieGenerator();
        cookieGenerator.setCookiePath("/");

        cookieGenerator.setCookieName(HttpHeader.SESSION);
        User user = accountsService.signin(request);
        cookieGenerator.addCookie(response, accountsService.getSessionId(user));
        return AccountsSignInResponse.valueOf(user);
    }
}
