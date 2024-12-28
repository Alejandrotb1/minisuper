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
		http
				.authorizeHttpRequests(auth -> auth
						// Bloquea acceso a "/registro" para el rol ROLE_CAJERO
						.requestMatchers("/registro/**").not().hasAuthority("ROLE_CAJERO")
						// Bloquea acceso a "/gastos" y sus rutas hijas para el rol ROLE_CAJERO
						.requestMatchers("/gastos/**").not().hasAuthority("ROLE_CAJERO")
						// Bloquea acceso a "/ingresos" y sus rutas hijas para el rol ROLE_CAJERO
						.requestMatchers("/ingresos/**").not().hasAuthority("ROLE_CAJERO")
						// Permite acceso a "/admin/**" solo para el rol ROLE_ADMIN
						.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
						// Permite acceso a "/user/**" solo para el rol ROLE_USER
						.requestMatchers("/user/**").hasAuthority("ROLE_USER")
						// Permite el acceso a recursos estáticos
						.requestMatchers("/js/**", "/css/**", "/img/**").permitAll()
						// Requiere autenticación para cualquier otra ruta
						.anyRequest().authenticated())
				.formLogin(form -> form
						.loginPage("/login").permitAll()
						.defaultSuccessUrl("/index", true)) // Redirección controlada
				.logout(logout -> logout
						.invalidateHttpSession(true)
						.clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/login?logout").permitAll())
				.exceptionHandling()

				.accessDeniedPage("/401");

		return http.build();
	}





}
