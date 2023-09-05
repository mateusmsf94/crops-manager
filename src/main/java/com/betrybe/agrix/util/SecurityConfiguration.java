package com.betrybe.agrix.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for Spring Security settings.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  /**
   * Configures the security filter chain.
   *
   * @param httpSecurity HttpSecurity object for configuring web security.
   * @return A SecurityFilterChain object.
   * @throws Exception if an error occurs.
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(
                    session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
  }

  /**
   * Configures the AuthenticationManager bean.
   *
   * @param authenticationConfiguration The AuthenticationConfiguration object.
   * @return An AuthenticationManager object.
   * @throws Exception if an error occurs.
   */
  @Bean
  public AuthenticationManager authenticationManager(
          AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /**
   * Configures the PasswordEncoder bean.
   *
   * @return A PasswordEncoder object.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

