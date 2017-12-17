package com.kakaobank.booksearch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestKakaoConfiguration {
//    @Bean
//    public RestTemplate getRestTemplate() {
//        ClientHttpRequest request = getRequestFactory().createRequest(url, method);
//        if (logger.isDebugEnabled()) {
//            logger.debug("Created " + method.name() + " request for \"" + url + "\"");
//        }
//        return request;

//        return new RestTemplate();

//        return new RestTemplate(new HttpClientFactory() {
//            @Override
//            public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
//                return null;
//            }
//            @Override
//            public HttpHeaders modifyHeaders(HttpHeaders headers) {
//                headers.add("Authorization", computeAuthString());
//                return headers;
//            }
//
//            public String computeAuthString() {
//                // do something better than this, but you get the idea
//                String credential = "b213e0be9fc3047b54714796f6f0ef5e";
//                return credential;
//            }
//        });
//    }
}
