package com.wook.prj01.web.member.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wook.prj01.web.token.JwtTokenProvider;
import com.wook.prj01.web.token.TokenInfo;

import lombok.RequiredArgsConstructor;

@Service("memberService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
//	@Autowired
//	private SqlSessionTemplate session;

	@Override
	public int login() {
//		System.out.println("memberImple!@!@!");
//		session.selectList("boardMapper.sadfsd");
		return 0;
	}
	
	
}
