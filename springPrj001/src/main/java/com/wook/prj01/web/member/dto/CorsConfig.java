package com.wook.prj01.web.member.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Value("${jwt.response.header}")
	private String jwtHeader;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:8081").exposedHeaders(jwtHeader) // 'Authorization'
																											// 헤더 값을
																											// 받아온다
				.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name(),
						HttpMethod.PUT.name(), HttpMethod.OPTIONS.name())
				.allowCredentials(true);
	}
}
