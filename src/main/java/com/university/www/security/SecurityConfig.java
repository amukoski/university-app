package com.university.www.security;

import com.github.mkopylec.recaptcha.security.login.FormLoginConfigurerEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private FormLoginConfigurerEnhancer enhancer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        enhancer.addRecaptchaSupport(http.formLogin())
        http.formLogin()
                    .loginPage("/login")
                    .and()
                .logout()
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/denied")
                    .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
//                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
