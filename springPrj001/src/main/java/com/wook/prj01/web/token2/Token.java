package com.wook.prj01.web.token2;

import lombok.Builder;
import lombok.Data;

@Data
public class Token {
	
	private String key;
    private String value;
	private Long expiredTime;
	
	@Builder
	public Token(String key, String value, Long expiredTime) {
		this.key = key;
		this.value = value;
		this.expiredTime = expiredTime;
	}
	
}
