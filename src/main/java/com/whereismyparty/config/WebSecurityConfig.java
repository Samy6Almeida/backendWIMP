package com.whereismyparty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }
    
    
/*
  @Configuration
 @EnableWebSecurity
 public class AuthorizeUrlsSecurityConfig extends WebSecurityConfigurerAdapter {

 	@Override
 	protected void configure(HttpSecurity http) throws Exception {
 		http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin();
 	}

 	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
 				.and().withUser("adminr").password("password").roles("ADMIN", "USER");
 	}
 }
 
We can also configure multiple URLs. The configuration below requiresauthentication to every URL and will grant access to URLs starting with /admin/ toonly the "admin" user. All other URLs either user can access.  @Configuration
 @EnableWebSecurity
 public class AuthorizeUrlsSecurityConfig extends WebSecurityConfigurerAdapter {

 	@Override
 	protected void configure(HttpSecurity http) throws Exception {
 		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
 				.antMatchers("/**").hasRole("USER").and().formLogin();
 	}

 	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
 				.and().withUser("adminr").password("password").roles("ADMIN", "USER");
 	}
 }
 
Note that the matchers are considered in order. Therefore, the following is invalidbecause the first matcher matches every request and will never get to the secondmapping:  http.authorizeRequests().antMatchers("/**").hasRole("USER").antMatchers("/admin/**")
 		.hasRole("ADMIN")

 
                 //.antMatchers("/api/story/**").authenticated()
                //.antMatchers("/api/user/**").authenticated()
 
 */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/api/story/**").hasAnyAuthority("USER","ROLE_ADMIN")  //.hasAuthority("USER")            
                .antMatchers("/api/party/**").hasAnyAuthority("USER","ROLE_ADMIN")
                .antMatchers("/api/parties/**").hasAnyAuthority("USER","ROLE_ADMIN")
                .antMatchers("/api/denunciation/**").hasAnyAuthority("USER","ROLE_ADMIN")
                .antMatchers("/api/denunciations/**").hasAnyAuthority("USER","ROLE_ADMIN")
                .antMatchers("/api/denunciations/**").hasAnyAuthority("USER","ROLE_ADMIN")
                .antMatchers("/userupdate/**").hasAnyAuthority("USER","ROLE_ADMIN")
                .antMatchers("/api/music/**").hasAnyAuthority("USER","ROLE_ADMIN")
                .antMatchers("/api/partykind/**").hasAnyAuthority("USER","ROLE_ADMIN")
                .antMatchers("/api/partyfeatures/**").hasAnyAuthority("USER","ROLE_ADMIN")
                .antMatchers("/api/user/**").hasAuthority("ROLE_ADMIN")
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FilterRegistrationBean platformCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration configAutenticacao = new CorsConfiguration();
        configAutenticacao.setAllowCredentials(true);
        configAutenticacao.addAllowedOrigin("*");
        configAutenticacao.addAllowedHeader("Authorization");
        configAutenticacao.addAllowedHeader("Content-Type");
        configAutenticacao.addAllowedHeader("Accept");
        configAutenticacao.addAllowedMethod("POST");
        configAutenticacao.addAllowedMethod("GET");
        configAutenticacao.addAllowedMethod("DELETE");
        configAutenticacao.addAllowedMethod("PUT");
        configAutenticacao.addAllowedMethod("OPTIONS");
        configAutenticacao.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", configAutenticacao);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(-110);
        return bean;
    }
}
