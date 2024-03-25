package com.wj.board.config;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/","/users/register","/boards/list/**","/comments/**","/css/**","/error").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/users/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/") // logout 성공 후 이동할 페이지
                        .invalidateHttpSession(true) // http 세션 초기화
                        .clearAuthentication(true) // 권한 정보 제거
                )

                /* 다중 로그인 설정
                   maximumSessions(1) 하나의 아이디에 대한 다중 로그인 허용 개수
                   maxSessionsPreventsLogin(true) 다중 로그인 개수를 초과하였을 경우 처리법
                   true - 초과시 새로운 로그인 차단 false - 초과시 기존 세션 하나 삭제 */
                .sessionManagement((sessionManagement) -> sessionManagement
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true));


                /* 세션 고정 공격 보호를 위한 세션 설정법
                   sessionFixation().changeSessionId());
                   none() : 로그인 시 세션 정보 변경 X
                   newSession() : 로그인 시 세션 새로 생성
                   changeSessionId() : 로그인 시 동일한 세션에 대한 id 변경 */
        http
                .sessionManagement((sessionManagement)->sessionManagement
                        .sessionFixation().changeSessionId());

        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    Hibernate5JakartaModule hibernate5Module() {
        return new Hibernate5JakartaModule();
    }
}