package com.aqours_challenge.our_challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class SessionConfig {

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("JSESSIONID"); // ✅ .jvmRoute 제거
        serializer.setUseHttpOnlyCookie(true);
        serializer.setUseSecureCookie(false); // HTTPS가 아니라면 false
        return serializer;
    }
}
