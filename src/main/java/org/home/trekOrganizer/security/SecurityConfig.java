package org.home.trekOrganizer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}password").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");

        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select login, password, enabled from manager where login = ?")
                .authoritiesByUsernameQuery("select login, role as authority from manager where login = ?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http
//                //HTTP Basic authentication
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET).hasRole("USER")
//                .antMatchers(HttpMethod.POST).hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
//                .and()
//                .csrf().disable()
//                .formLogin().disable();

//        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
//
//
////        http.csrf().ignoringAntMatchers("/h2-console/**");
//
//        http.headers().frameOptions().sameOrigin();

        http.cors().and().csrf().disable();

        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/**").hasAnyRole("ADMIN", "USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/v1/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/v1/**").hasRole("ADMIN");

        http.httpBasic();

        http.headers().frameOptions().sameOrigin();


    }


}
