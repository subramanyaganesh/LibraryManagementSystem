package com.project.LibraryManagement.securityconfig;


import com.project.LibraryManagement.Service.LibrarianService;
import com.project.LibraryManagement.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private LibrarianService librarianService;
    @Autowired
    private MemberService memberService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        auth.inMemoryAuthentication().withUser("admin@admin.com")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .and()
                .withUser("member@gmail.com")
                .password(encoder.encode("user"))
                .roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/librarian/**")
                .hasRole("ADMIN")
                .and()
                .httpBasic()
                .and();
        http.csrf().disable();


    }




   /* @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(librarianService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider1() {
        var auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(memberService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        //auth.authenticationProvider(authenticationProvider1());
    }*/

}
