package nl.veenm.novi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static nl.veenm.novi.security.ApplicationUserRole.*;
import static nl.veenm.novi.security.ApplicationUserPermission.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/customer/api/**").hasRole(CUSTOMER.name())
                .antMatchers("/restaurant/api/**").hasAnyRole(MANAGER.name(), COURIER.name(), CHEF.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails novi = User.builder()
                .username("novi")
                .password(passwordEncoder.encode("bootcamp"))
                .roles(MANAGER.name()) //ROLE_MANAGER
                .build();

        UserDetails lasse = User.builder()
                .username("lasse")
                .password(passwordEncoder.encode("novi2021"))
                .roles(CHEF.name()) //ROLE_CHEF
                .build();


        UserDetails veenm = User.builder()
                .username("veenm")
                .password(passwordEncoder.encode("java2021"))
                .authorities(CUSTOMER.getGrantedAuthorities())
                .build();

        UserDetails bossf = User.builder()
                .username("bossf")
                .password(passwordEncoder.encode("java2021"))
                .roles(COURIER.name()) //ROLE_COURIER
                .build();

        return new InMemoryUserDetailsManager(
                novi,
                veenm,
                lasse,
                bossf
        );

    }
}
