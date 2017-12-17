package com.kakaobank.booksearch.domain.redis;

import com.kakaobank.booksearch.domain.jpa.User;
import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("sessions")
@Data
@EqualsAndHashCode
@ToString
public class Session {
	
	@Id
	private String id;

    private Value value;
    
//    @TimeToLive(unit = TimeUnit.DAYS)
//    private Long expiration = 30L;
    
    @Data
    @EqualsAndHashCode
    @ToString
    public static class Value {
    	
    	private long userId;

    	
    	public static Value valueOf(User user) {
    		Value value = new Value();
    		value.setUserId(user.getId());
    		
    		return value;
    	}
    }
}