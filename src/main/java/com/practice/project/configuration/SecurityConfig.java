package com.practice.project.configuration;

import com.practice.project.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;
    private CustomUserDetailService customUserDetailService;
    public SecurityConfig(CustomUserDetailService theCustomUserDetailService) {
        customUserDetailService = theCustomUserDetailService;
    }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector).servletPath("/e-catalog");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, MvcRequestMatcher.Builder mvcBuilder) throws Exception {
        httpSecurity.authorizeHttpRequests(requests -> requests
            .requestMatchers(
                mvcBuilder.pattern("/h2-console/**"),
                mvcBuilder.pattern("/"),
                mvcBuilder.pattern("/shop/**"),
                mvcBuilder.pattern("/register"),
                mvcBuilder.pattern("/resources/**"),
                mvcBuilder.pattern("/static/**"),
                mvcBuilder.pattern("/css/**"),
                mvcBuilder.pattern("/images/**"),
                mvcBuilder.pattern("/productImages/**"),
                mvcBuilder.pattern("/js/**")
            ).permitAll()
            .requestMatchers(mvcBuilder.pattern("/admin/**")).hasRole("ADMIN")
            .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
                .failureUrl("/login?error=true")
                .successHandler(customAuthenticationSuccessHandler())
                .passwordParameter("password")
                .usernameParameter("email")
            )
            .oauth2Login(oauth -> oauth
                .loginPage("/login")
                .successHandler(googleOAuth2SuccessHandler)
                .failureUrl("/login?error=true")
                .successHandler(customAuthenticationSuccessHandler())
            )
            .exceptionHandling(configurer -> configurer
                .accessDeniedHandler((request, response, accessDeniedException) -> response.sendRedirect("/access-denied"))
                .accessDeniedPage("/access-denied")
            )
            .logout(logout -> logout
                .permitAll()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            )
            .csrf(httpSecurityCsrfConfigurer ->
                httpSecurityCsrfConfigurer.disable())
            .headers(headers ->
                headers.disable()
            );

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            if (authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
                response.sendRedirect("/admin");
            } else {
                response.sendRedirect("/");
            }
        };
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(customUserDetailService);
    }
}
