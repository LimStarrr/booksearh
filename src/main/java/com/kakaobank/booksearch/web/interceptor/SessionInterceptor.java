package com.kakaobank.booksearch.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaobank.booksearch.common.Attribute;
import com.kakaobank.booksearch.common.HttpHeader;
import com.kakaobank.booksearch.domain.redis.Session;
import com.kakaobank.booksearch.exception.CustomException;
import com.kakaobank.booksearch.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class SessionInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private AccountsService accountsService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String sessionCookie = null;
		
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (HttpHeader.SESSION.equals(cookie.getName())) {
					sessionCookie = cookie.getValue();
				}
			}
		}
		
		if (StringUtils.isEmpty(sessionCookie)) {
			throw new CustomException(HttpStatus.UNAUTHORIZED, "Session cookie is empty");
		}
		
		
		Session session = accountsService.getSession(sessionCookie);
		if (session == null) {
			throw new CustomException(HttpStatus.UNAUTHORIZED, "Stored session is null");
		}
		
		request.setAttribute(Attribute.SESSION, session);
		
		return super.preHandle(request, response, handler);
	}
}
