package com.inventario.inventario.security;

import com.inventario.inventario.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration {

	private final UsuarioService usuarioService;
	private final BCryptPasswordEncoder passwordEncoder;

	public SecurityConfiguration(UsuarioService usuarioService, BCryptPasswordEncoder passwordEncoder) {
		this.usuarioService = usuarioService;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(usuarioService);
		auth.setPasswordEncoder(passwordEncoder);
		return auth;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(auth -> auth
//						.requestMatchers("/registro**", "/js/**", "/css/**", "/img/**").permitAll()
//						.anyRequest().authenticated())
//				.formLogin(form -> form
//						.loginPage("/login").permitAll())
//				.logout(logout -> logout
//						.invalidateHttpSession(true)
//						.clearAuthentication(true)
//						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//						.logoutSuccessUrl("/login?logout").permitAll());
//		return http.build();
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
						.requestMatchers("/registro").not().hasAuthority("ROLE_CAJERO")  // El rol CAJERO no puede acceder a /registro
						.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")  // Admin tiene acceso completo a /admin/**
						.requestMatchers("/user/**").hasAuthority("ROLE_USER")   // User puede acceder a /user/**
						.requestMatchers("/js/**", "/css/**", "/img/**").permitAll()  // Acceso libre a los recursos estáticos
						.anyRequest().authenticated())  // Otras rutas requieren autenticación
				.formLogin(form -> form
						.loginPage("/login").permitAll())  // Página de login accesible para todos
				.logout(logout -> logout
						.invalidateHttpSession(true)
						.clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/login?logout").permitAll());  // Logout accesible para todos
		return http.build();
	}


}
