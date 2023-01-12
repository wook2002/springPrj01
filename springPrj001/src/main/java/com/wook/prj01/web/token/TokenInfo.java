package com.wook.prj01.web.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder  // 객체생성방법 같은것
@Data
@AllArgsConstructor
public class TokenInfo {
	private String grantType; // jwt 인증타입 Bearer사용 (Bearer : JWT 혹은 OAuth에 대한 토큰을 사용한다. (RFC 6750))
    private String accessToken; 
    private String refreshToken;
}
/*
 * 헤더 : 암호화방식, 타입
 * 페이로드 : 데이터
 * 서명 : 검증을 위해 각각 인코딩(해싱)한 값이 포함됨*/
