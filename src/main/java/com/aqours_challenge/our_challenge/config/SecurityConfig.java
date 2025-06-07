package com.aqours_challenge.our_challenge.config;

import com.aqours_challenge.our_challenge.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
    @Autowired
    MemberService memberService;

    @Bean
    protected SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                    .requestMatchers("/","/error","/css/**", "/members/**", "/gallery").permitAll()
                    .requestMatchers("/img/**","/images/**","/yosegaki/**").permitAll()
                    .requestMatchers("/api/image/**").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/posts/**","/mypage/**").hasAnyRole("USER", "ADMIN","STAFF")
                    .requestMatchers("/api/**").hasAnyRole("USER", "ADMIN","STAFF")
                    .anyRequest().authenticated()
                )
                .formLogin((formLogin) -> formLogin
                    .loginPage("/members/login")
                    .permitAll()
                    .defaultSuccessUrl("/")
                    .usernameParameter("email")
                    .failureUrl("/members/login/error")
                )
                .logout((logout) -> logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                    .logoutSuccessUrl("/")
                )
                .sessionManagement(sessionManagementCustomizer -> sessionManagementCustomizer
                    // 세션 생성 정책 설정
//                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // 스프링시큐리티가 항상 세션을 생성
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 스프링시큐리티가 필요시 생성(기본)
//                    .sessionCreationPolicy(SessionCreationPolicy.NEVER) // 스프링시큐리티가 생성하지않지만, 기존에 존재하면 사용
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 스프링시큐리티가 생성하지도않고 기존것을 사용하지도 않음. JWT 같은토큰방식을 쓸때 사용하는 설정
                    .maximumSessions(1) // 최대 세션 수 제한
                    .expiredUrl("/members/login?expired=true")  // 세션 만료 시 이동할 URL
                )
                .rememberMe(rememberMeCustomizer -> rememberMeCustomizer // 'remember me' 기능 활성화
                    .key("uniqueAndSecret")  // rememberMe 토큰의 암호화 키
                    .tokenValiditySeconds(60*60*24*7)  // rememberMe 토큰 유효 시간 설정 (7days)
                ).userDetailsService(memberService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * static resource 인증 무시
     * @return
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
