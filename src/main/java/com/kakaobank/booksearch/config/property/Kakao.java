package com.kakaobank.booksearch.config.property;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kakao")
@Data
@EqualsAndHashCode
@ToString
public class Kakao {
    String url;
    String authorization;
}
