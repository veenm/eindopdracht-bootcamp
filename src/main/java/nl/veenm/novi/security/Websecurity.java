package nl.veenm.novi.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class Websecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and()
                .csrf().disable().authorizeRequests()
                .antMatchers("/employee").hasRole("manager")
                .anyRequest().authenticated().and().formLogin();
    }


}
