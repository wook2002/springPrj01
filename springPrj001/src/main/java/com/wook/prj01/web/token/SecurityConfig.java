package com.wook.prj01.web.token;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;


// https://gksdudrb922.tistory.com/217
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtTokenProvider jwtTokenProvider;
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	System.out.println("sec-SecurityFilterChain-http : " + http);
        http
                .httpBasic().disable()
                .csrf().disable() 
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and() // basic auth,csrf보안,session 사용(x)
                .authorizeRequests()
                .antMatchers("/members/login").permitAll()
                .antMatchers("/members/test").hasRole("USER")
                .anyRequest().authenticated() //  이 밖에 모든 요청에 대해서 인증필요
                .and()
        //WT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행하겠다는 설정
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
 
//   JWT를 사용하기 위해서는 기본적으로 password encoder가 필요한데, 
//    여기서는 Bycrypt encoder를 사용했다.
    @Bean
    public PasswordEncoder passwordEncoder() {
    	PasswordEncoder passwordEndcoder = new BCryptPasswordEncoder();
    	System.out.println("sec-passwordEndcoder : " + passwordEndcoder);
    	return passwordEndcoder;
//      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    	https://java.ihoney.pe.kr/498
    }

}
