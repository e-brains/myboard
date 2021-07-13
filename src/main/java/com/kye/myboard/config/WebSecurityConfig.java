package com.kye.myboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/index").permitAll() // 로그인 없이 접근 가능
                    .anyRequest().authenticated()  // 그 밖에 요청은 로그인 권한이 필요함
                    .and()
                .formLogin()   // 폼 로그인을 별도로 설정해서 사용하겠다는 의미
                    .loginPage("/login")   // 로그인 페이지 지정
                    .permitAll()  // 로그인 없이 로그인 페이지에 접근 가능
                    .and()
                .logout()
                    .permitAll();
    }
}
