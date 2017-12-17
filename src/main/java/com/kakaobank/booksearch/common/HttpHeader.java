package com.kakaobank.booksearch.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class HttpHeader {
	
	public static final String SESSION = "session";
	
	public static final String X_APP_VERSION = "X-APP-Version";
	
	public static final String X_DEVICE = "X-Device";
	
	public static final String ACCEPT_LANGUAGE = "Accept-Language";
	
	
	private String appVersion;
	
	private String device;
	
	private String acceptLanguage;
}
