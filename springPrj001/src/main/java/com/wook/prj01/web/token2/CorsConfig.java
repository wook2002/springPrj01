package com.wook.prj01.web.token2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer{
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
		System.out.println("123123");
        registry.addMapping("/**")
//        	.allowedOriginPatterns("*")
            .allowedOrigins("http://localhost:8081")
            .allowedMethods("*")
            .allowCredentials(true);
        System.out.println("456456");
    }

}
