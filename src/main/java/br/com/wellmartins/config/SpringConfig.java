package br.com.wellmartins.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SpringConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() // autenticacao em memoria 
		.withUser("admin").password("{noop}admin").roles("ROLE");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/categoria/**").permitAll() //permitindo requisicoes pra esse endpoint
		.anyRequest().authenticated().and() // qualquer outra requisição precisa de autorizacao
		.httpBasic().and()// tipo de autenticao é http basica
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // desabilitando sessão
		.csrf().disable(); //desabilitando csrf
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
	    User.UserBuilder builder = User.withDefaultPasswordEncoder();
	    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	    manager.createUser(builder.username("admin").password("admin").roles("ROLE").build());
	    return manager;
	}
	

}
