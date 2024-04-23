package com.example.crystalworld.config;

import com.example.crystalworld.model.enums.RoleEnum;
import com.example.crystalworld.repository.UserRepository;
import com.example.crystalworld.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, SecurityContextRepository securityContextRepository) throws Exception {

        httpSecurity.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/", "/static/**", "/images/**", "/css/**", "/css/responsive/**","/login", "/register", "/aboutUs", "/contacts", "/shop",
                        "/products/{type}", "/downloadPicture/{productId}", "/login-error").permitAll()
                        .requestMatchers("/admin", "/products/addProduct", "/deleteProduct/{productId}").hasAuthority(RoleEnum.ADMIN.name())
                        .requestMatchers("/users/moderator", "/deleteOrder/{orderId}").hasAuthority(RoleEnum.MODERATOR.name())
                        .anyRequest().authenticated())
                .formLogin((form) ->
                        form.loginPage("/login")
                                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                                .defaultSuccessUrl("/", true)
                                .failureForwardUrl("/login-error"))
                .logout((logout) ->
                        logout.logoutUrl("/logout").invalidateHttpSession(true)
                                .logoutSuccessUrl("/"))
                .securityContext((securityContext) ->
                        securityContext.securityContextRepository(securityContextRepository));

        return httpSecurity.build();

    }

    @Bean
    public SecurityContextRepository securityContextRepository(){
        return new DelegatingSecurityContextRepository(new RequestAttributeSecurityContextRepository(), new HttpSessionSecurityContextRepository());
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }
}
