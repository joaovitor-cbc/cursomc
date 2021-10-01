package com.example.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.cursomc.security.JWTAuthenticationFilter;
import com.example.cursomc.security.JWTAuthorizationFilter;
import com.example.cursomc.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private Environment env;
	
	@Autowired
	private UserDetailsService detailsService;
	
	@Autowired
	private JWTUtil jwtUtil;

	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**"
	};

	private static final String[] PUBLIC_MATCHERS_GET = {
			"/produtos/**",
			"/categorias/**",
			"/estados/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
			"/clientes/**",
			"/auth/forgot/**"
	};
	
	//configuração Spring Security
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// config para liberar o h2
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }
		
		http.cors().and().csrf().disable();
		// liberação de endpoints e metodos http
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated();
		//filtro de autenticação
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		//filtro de autorização
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, detailsService));
		// para o back-end não salvar nenhuma sessão do usuario
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	//informa qual a cryptografia usada e qual é o detailsService ao SS
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	//configuração de cors
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	//para liberar o acesso do swagger
	@Override
	public void configure(WebSecurity web)throws Exception {
	web.ignoring().antMatchers("/v2/api-docs","/configuration/ui","/swagger-resources/**","/configuration/**",
	"/swagger-ui.html","/webjars/**");
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
