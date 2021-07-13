package com.kye.myboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/index", "/css/**", "/js/**", "/img/**").permitAll() // 로그인 없이 접근 가능
                    .anyRequest().authenticated()  // 그 밖에 요청은 로그인 권한이 필요함
                    .and()
                .formLogin()   // 폼 로그인을 별도로 설정해서 사용하겠다는 의미
                    .loginPage("/account/loginForm")   // 로그인 페이지 지정
                    .permitAll()  // 로그인 없이 로그인 페이지에 접근 가능
                    .and()
                .logout()
                    .permitAll();
    }

    public void configurationGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username, password, enabled "  // 컬럼 순서 주의
                    + "from user "
                    + "where username = ?")
                .authoritiesByUsernameQuery("select username, name "
                        + "from r_user_role ur inner join user u on ur.user_id = u.id "
                        + "inner join role r on ur.role_id = r.id "
                        + "where username");
    }

}
