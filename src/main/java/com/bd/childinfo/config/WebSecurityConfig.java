package com.bd.childinfo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bd.childinfo.security.ChildInfoAccessDeniedHandler;
import com.bd.childinfo.security.ChildInfoAuthenticationProvider;
import com.bd.childinfo.security.SecurityAuthenticationFailureHandler;
import com.bd.childinfo.security.SecurityAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ChildInfoAuthenticationProvider authenticationProvider;

    @Autowired
    private ChildInfoAccessDeniedHandler childInfoAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .authorizeRequests()
                .antMatchers("/", "/termsofservice", "/change-password", "/reset-password", "/user/register", "/signup", "/login", "/login-error/*",
                        "/api/v1/about",
                        "/webjars/**", "/css/**", "/js/**", "/images/**", "/img/**", "/font-awesome-4.1.0/**").permitAll()
                .antMatchers("/user/profile").authenticated() //hasAnyRole( "USER", "ADMIN" )
                .antMatchers("/user/**", "/organization/**", "/admin/**").hasAnyRole("APPLICATION", "ADMIN")
                .antMatchers("/organizations/search", "/organizations/search?*").authenticated() //hasAnyRole("ADMIN", "USER")
                .antMatchers("/organizations/*").authenticated()
                .antMatchers("/programs/*").authenticated()
                .antMatchers("/programs/create/*").hasAuthority("ADMIN")
                .antMatchers("/program/**").authenticated()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/api/**").hasAnyRole("APPLICATION", "ADMIN", "USER")
                .antMatchers("/api/v1/registration/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/media/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(new SecurityAuthenticationSuccessHandler())
                .failureHandler(new SecurityAuthenticationFailureHandler())
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .authenticationProvider(authenticationProvider)
                .exceptionHandling().accessDeniedHandler(childInfoAccessDeniedHandler);
    }

}
