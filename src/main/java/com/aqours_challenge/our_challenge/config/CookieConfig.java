package com.aqours_challenge.our_challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class CookieConfig {
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieName("SESSIONID");  // 쿠키 이름 설정
        cookieSerializer.setCookiePath("/");  // 쿠키 경로 설정
        cookieSerializer.setDomainNamePattern(".*yourdomain.com");  // 도메인 설정
        cookieSerializer.setUseSecureCookie(true);  // HTTPS로만 쿠키를 보내도록 설정
        cookieSerializer.setUseHttpOnlyCookie(true);  // 클라이언트 JavaScript에서 쿠키에 접근하지 못하도록 설정
        cookieSerializer.setSameSite("Strict");  // SameSite 설정
        return cookieSerializer;
    }
}
