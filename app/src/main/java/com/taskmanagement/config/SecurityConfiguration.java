package com.taskmanagement.config;

import com.taskmanagement.domain.common.security.AccessDeniedHandlerImpl;
import com.taskmanagement.web.apis.authenticate.AuthenticationFilter;
import com.taskmanagement.web.apis.authenticate.SimpleAuthenticationFailureHandler;
import com.taskmanagement.web.apis.authenticate.SimpleAuthenticationSuccessHandler;
import com.taskmanagement.web.apis.authenticate.SimpleLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


/**
 * 스프링 시큐리티를 위한 설정 클래스
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final String[] PUBLIC = new String[] {
    "/error", "/login", "/logout", "/register", "/api/registrations"
  };

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .antMatchers(PUBLIC).permitAll()
        .anyRequest().authenticated()
      .and()
        .formLogin()
        .loginPage("/login")
      .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login?logged-out")
      .and()
        .csrf().disable();

//    http.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
//      .and()
//      .authorizeRequests()
//      .antMatchers(PUBLIC).permitAll()
//      .anyRequest().authenticated()
//      .and()
//      .addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//      .formLogin()
//      .loginPage("/login")
//      .and()
//      .logout()
//      .logoutUrl("/logout")
//      .logoutSuccessHandler(logoutSuccessHandler())
//      .and()
//      .csrf().disable();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
      .antMatchers("/static/**", "/js/**", "/css/**", "/images/**", "/favicon.ico");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationFilter authenticationFilter() throws Exception {
    AuthenticationFilter authenticationFilter = new AuthenticationFilter();
    authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
    authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
    authenticationFilter.setAuthenticationManager(authenticationManagerBean());
    return authenticationFilter;
  }

  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new SimpleAuthenticationSuccessHandler();
  }

  @Bean
  public AuthenticationFailureHandler authenticationFailureHandler() {
    return new SimpleAuthenticationFailureHandler();
  }

  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
    return new SimpleLogoutSuccessHandler();
  }

  public AccessDeniedHandler accessDeniedHandler() {
    return new AccessDeniedHandlerImpl();
  }
}
