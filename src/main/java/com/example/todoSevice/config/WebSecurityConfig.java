package com.example.todoSevice.config;

import com.example.todoSevice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // Включаем авторизацию
                    .antMatchers("/", "/registration").permitAll() // Даем полный доступ на страничку
                    .anyRequest().authenticated() // Для всех остальных запросов требуем авторизацию
                .and()
                    .formLogin() // включаем форму логин
                    .loginPage("/login") // указываем что страница логина у нас наъодиться на таком мэпинге
                    .permitAll() // Разрешаем этим пользоваться всем
                .and()
                    .logout() // включаем логаут
                    .permitAll(); // разрешаем всем им пользоваться
    }

    /* Это менеджер который обслуживает учетные записи пользователей */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
