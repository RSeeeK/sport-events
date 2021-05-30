package ru.devhack.motomoto.sportevents.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Spring security configuration
 *
 * @author AR
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] SWAGGER_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/v3/api-docs",
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-ui",
            "/webjars/**",
            "/swagger-resources/**",
            "/swagger-resources"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(SWAGGER_WHITELIST).permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/test/**").permitAll()
                .anyRequest().authenticated();

        //http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
