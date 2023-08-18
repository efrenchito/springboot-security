package com.learningspring.springbootsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
               .antMatchers(HttpMethod.GET).permitAll()
               .anyRequest().authenticated()
            .and().httpBasic();

    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        //UserDetails user = User.builder().username("ediaz").password("ediaz").roles("USER").build();
        //UserDetails admin=User.builder().username("admin").password("admin").roles("ADMIN").build();

        UserDetails user = User.builder()
                .username("ediaz")
                .password(passwordEncoder().encode("ediaz"))
                .roles("USER").build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);

    }
}

