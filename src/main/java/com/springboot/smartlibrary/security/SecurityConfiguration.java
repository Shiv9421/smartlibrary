package com.springboot.smartlibrary.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("admin")).roles("Admin");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
    	System.out.println("inside configure");
        //http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests().antMatchers("/login","/h2").permitAll()
                .antMatchers("/").access("hasRole('admin')")
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin().loginPage("/login")

                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    for(GrantedAuthority authority:authentication.getAuthorities())
                    {
                        System.out.println(authority.getAuthority());
                    }
                    httpServletResponse.sendRedirect("/home");
                })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    String errMsg="";
                    if(e.getClass().isAssignableFrom(BadCredentialsException.class))
                    {
                        errMsg= "Invalid Username & Password";
                    }
                    else{
                        errMsg="BadCredentials";
                    }
                    httpServletRequest.getSession().setAttribute("errorMessage",errMsg);
                    httpServletResponse.sendRedirect("/login");
                })
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .csrf().disable()
                ;
    }
    
}
